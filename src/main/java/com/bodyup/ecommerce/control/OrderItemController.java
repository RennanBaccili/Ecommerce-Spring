package com.bodyup.ecommerce.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodyup.ecommerce.model.OrderItem;
import com.bodyup.ecommerce.services.OrderItemService;



//controlador da camada rest
@RestController
@RequestMapping(value= "/orders-item")
public class OrderItemController {

	@Autowired
	private OrderItemService service;

	
	//mapeado no padrão rest
	@GetMapping
	public ResponseEntity<List<OrderItem>> findAll(){
		List<OrderItem> o = service.findAll();
		return ResponseEntity.ok().body(o);
	}
	
	
	
}
