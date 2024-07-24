package com.zivame.orderapp.models;

public class PlaceOrder {
	private int customer_id;
	private int product_id;
	private int quantity;
	
	public PlaceOrder() {	
	}
	
	public PlaceOrder(int customer_id,int product_id,int quantity) {
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.quantity = quantity;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
