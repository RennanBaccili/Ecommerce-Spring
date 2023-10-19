package com.bodyup.ecommerce.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodyup.ecommerce.model.Product;
import com.bodyup.ecommerce.services.ProductService;



//controlador da camada rest
@RestController
@RequestMapping(value= "/product")
public class ProductController {

	@Autowired
	private ProductService service;
	//test
	
	//mapeado no padrão rest
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> u = service.findAll();
		return ResponseEntity.ok().body(u);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id){
		Product u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}
	

	
}
