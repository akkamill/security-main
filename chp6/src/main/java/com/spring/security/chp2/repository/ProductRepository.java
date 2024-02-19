package com.spring.security.chp2.repository;

import com.spring.security.chp2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}