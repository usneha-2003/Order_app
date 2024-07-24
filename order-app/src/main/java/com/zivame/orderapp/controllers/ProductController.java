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

import com.zivame.orderapp.models.Product;
import com.zivame.orderapp.repositories.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public List<Product> list(){
		return productRepository.findAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Optional<Product> get(@PathVariable Integer id) {
		return productRepository.findById(id);
	}
	
	@PostMapping
	public Product create(@RequestBody final Product product) {
		return productRepository.saveAndFlush(product); 
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		productRepository.deleteById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public Product update(@PathVariable Integer id,@RequestBody Product product) {
		Product existingProduct = productRepository.findById(id).get();
		BeanUtils.copyProperties(product, existingProduct, "product_id");
		return productRepository.saveAndFlush(existingProduct);
	}
}
