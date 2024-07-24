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

import com.zivame.orderapp.models.Customer;
import com.zivame.orderapp.repositories.CustomerRepository;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping
	public List<Customer> list(){
		return customerRepository.findAll(); 
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Optional<Customer> get(@PathVariable int id) {
		return customerRepository.findById(id);
	}
	
	@PostMapping
	public Customer create(@RequestBody final Customer customer) {
		return customerRepository.saveAndFlush(customer); 
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		customerRepository.deleteById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Customer update(@PathVariable int id,@RequestBody Customer customer) {
		Customer existingCustomer = customerRepository.findById(id).get();
		BeanUtils.copyProperties(customer, existingCustomer, "customer_id");
		return customerRepository.saveAndFlush(existingCustomer);
	}
}
