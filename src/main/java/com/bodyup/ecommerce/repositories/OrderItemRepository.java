package com.bodyup.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bodyup.ecommerce.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
