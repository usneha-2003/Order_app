package com.zivame.orderapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zivame.orderapp.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
