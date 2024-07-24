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

import com.zivame.orderapp.models.Payment;
import com.zivame.orderapp.repositories.PaymentRepository;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentRepository paymentRepository;
	
	@GetMapping
	public List<Payment> list(){
		return paymentRepository.findAll(); 
	}
	
	@GetMapping
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Optional<Payment> get(@PathVariable int id) {
		return paymentRepository.findById(id);
	}
	
	@PostMapping
	public Payment create(@RequestBody final Payment payment) {
		return paymentRepository.saveAndFlush(payment); 
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		paymentRepository.deleteById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Payment update(@PathVariable int id,@RequestBody Payment payment) {
		Payment existingPayment = paymentRepository.findById(id).get();
		BeanUtils.copyProperties(payment, existingPayment, "payment_id");
		return paymentRepository.saveAndFlush(existingPayment);
	}
}
