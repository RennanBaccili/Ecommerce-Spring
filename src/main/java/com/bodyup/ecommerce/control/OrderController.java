package com.bodyup.ecommerce.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodyup.ecommerce.model.Order;
import com.bodyup.ecommerce.services.OrderService;



//controlador da camada rest
@RestController
@RequestMapping(value= "/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	
	//mapeado no padrão rest
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> o = service.findAll();
		return ResponseEntity.ok().body(o);
	}
	
	
	
}
