package com.zivame.orderapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zivame.orderapp.models.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer>{
	OrderDetails findByOrderId(int order_id);
	void deleteByOrderId(int order_id);
}
