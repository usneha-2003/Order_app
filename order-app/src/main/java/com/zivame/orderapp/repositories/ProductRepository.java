package com.zivame.orderapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zivame.orderapp.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
