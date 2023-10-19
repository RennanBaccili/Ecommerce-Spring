package com.bodyup.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bodyup.ecommerce.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
