package com.zivame.orderapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="order_details")
public class OrderDetails {
	//@EmbeddedId
	//OrderDetailsKey id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_details_id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@MapsId("orderid")
    @JoinColumn(name = "order_id", nullable = false,insertable = false,updatable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Order order;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@MapsId("productid")
    @JoinColumn(name = "product_id", nullable = false, insertable = false,updatable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Product product;
	
	private int quantity;
	private int total_price;
	@Column(name = "order_id")
    private int orderId;
	private int product_id;
	
	public OrderDetails() {
		
	}
	
	public OrderDetails(int order_id, int product_id, int quantity) {
		this.orderId = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.total_price = 0;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getOrder_details_id() {
		return order_details_id;
	}
	public void setOrder_details_id(int order_details_id) {
		this.order_details_id = order_details_id;
	}

	public int getOrder_id() {
		return orderId;
	}

	public void setOrder_id(int order_id) {
		this.orderId = order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}	
	
}
