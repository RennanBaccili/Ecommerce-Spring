package com.bodyup.ecommerce.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodyup.ecommerce.model.Category;
import com.bodyup.ecommerce.services.CategoryService;



//controlador da camada rest
@RestController
@RequestMapping(value= "/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	
	//mapeado no padrão rest
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		List<Category> o = service.findAll();
		return ResponseEntity.ok().body(o);
	}
	
	
	
}
