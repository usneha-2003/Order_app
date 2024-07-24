package com.zivame.orderapp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zivame.orderapp.models.Order;
import com.zivame.orderapp.models.OrderDetails;
import com.zivame.orderapp.models.Payment;
import com.zivame.orderapp.models.Product;
import com.zivame.orderapp.repositories.OrderDetailsRepository;
import com.zivame.orderapp.repositories.OrderRepository;
import com.zivame.orderapp.repositories.PaymentRepository;
import com.zivame.orderapp.repositories.ProductRepository;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public boolean placeOrder(int customer_id, int product_id, int quantity) {
		Product product = productRepository.findById(product_id).get();
		if(product.getProduct_quantity()<quantity){
			return false;
		}
		product.setProduct_quantity(product.getProduct_quantity()-quantity);
		productRepository.save(product);
		
		Order order = new Order(customer_id);
		int order_id = orderRepository.save(order).getOrder_id();
		//System.out.println(order_id);
		
		OrderDetails orderDetails = new OrderDetails(order_id,product_id,quantity);
		orderDetails.setTotal_price(quantity*product.getProduct_price());
		orderDetailsRepository.save(orderDetails);
		
		Payment payment = new Payment(order_id);
		payment.setAmount_paid(orderDetails.getTotal_price());
		paymentRepository.save(payment);
		
		order.setOrder_status("shipped");
		orderRepository.save(order);
		return true;
	}
	
	public void cancelOrder(int order_id) {
		OrderDetails orderDetails = orderDetailsRepository.findByOrderId(order_id);
		int product_id = orderDetails.getProduct_id();
		int quantity = orderDetails.getQuantity();
		
		Product product = productRepository.findById(product_id).get();
		product.setProduct_quantity(product.getProduct_quantity()+quantity);
		productRepository.save(product);
		
		paymentRepository.deleteByOrderId(order_id);
		orderDetailsRepository.deleteByOrderId(order_id);
	}

	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Order> findByOrderId(int id) {
		return orderRepository.findById(id);
	}

	public void deleteByOrderId(int id) {
		orderRepository.deleteById(id);
	}

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
}
