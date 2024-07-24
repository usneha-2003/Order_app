package com.zivame.orderapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zivame.orderapp.models.OrderDetails;
import com.zivame.orderapp.repositories.OrderDetailsRepository;

@RestController
@RequestMapping("/order_details")
public class OrderDetailsController {
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@GetMapping
	public List<OrderDetails> list(){
		return orderDetailsRepository.findAll(); 
	}
	
	@GetMapping
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Optional<OrderDetails> get(@PathVariable int id) {
		return orderDetailsRepository.findById(id);
	}
	
	@PostMapping
	public OrderDetails create(@RequestBody final OrderDetails orderDetails) {
		return orderDetailsRepository.saveAndFlush(orderDetails); 
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		orderDetailsRepository.deleteById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public OrderDetails update(@PathVariable int id,@RequestBody OrderDetails orderDetails) {
		OrderDetails existingOrderDetails = orderDetailsRepository.findById(id).get();
		BeanUtils.copyProperties(orderDetails, existingOrderDetails, "order_details_id");
		return orderDetailsRepository.saveAndFlush(existingOrderDetails);
	}
}
