package com.bodyup.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bodyup.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
