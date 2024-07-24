package com.zivame.orderapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zivame.orderapp.models.Order;
import com.zivame.orderapp.models.PlaceOrder;
import com.zivame.orderapp.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public List<Order> list(){
		return orderService.findAllOrders(); 
	}
	
	@GetMapping
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Optional<Order> get(@PathVariable int id) {
		return orderService.findByOrderId(id);
	}
	
	@PostMapping
	public ResponseEntity<Order> create(@RequestBody PlaceOrder placeOrder) {
		boolean msg = orderService.placeOrder(placeOrder.getCustomer_id(), placeOrder.getProduct_id(), placeOrder.getQuantity());
		if(!msg) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		orderService.cancelOrder(id);
		orderService.deleteByOrderId(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Order update(@PathVariable int id,@RequestBody Order order) {
		Order existingOrder = orderService.findByOrderId(id).get();
		BeanUtils.copyProperties(order, existingOrder, "order_id");
		return orderService.saveOrder(existingOrder);
	}
}
