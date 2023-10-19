package com.bodyup.ecommerce.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodyup.ecommerce.model.OrderItem;
import com.bodyup.ecommerce.repositories.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository repository;
	
	public List<OrderItem> findAll(){
		 return repository.findAll();
	}

	public OrderItem findById(Long id) {
		Optional<OrderItem> order = repository.findById(id);
		return order.get();
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
