package com.zivame.orderapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payment_id;
	private Date payment_date;
	private int amount_paid;
	
	@Column(name = "order_id")
    private int orderId;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="order_id", nullable = false, insertable = false,updatable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Order order;
	
	public Payment() {
		
	}
	
	public Payment(int order_id) {
		this.orderId = order_id;
		this.payment_date = new Date();
		this.amount_paid = 0;
	}

	public int getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public int getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(int amount_paid) {
		this.amount_paid = amount_paid;
	}
	public int getOrder_id() {
		return orderId;
	}

	public void setOrder_id(int order_id) {
		this.orderId = order_id;
	}
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
