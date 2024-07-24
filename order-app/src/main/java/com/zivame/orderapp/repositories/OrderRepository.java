package com.zivame.orderapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zivame.orderapp.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
	
}
