package com.bodyup.ecommerce.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodyup.ecommerce.model.User;



//controlador da camada rest
@RestController
@RequestMapping(value= "/users")
public class UserController {

	//test
	
	//mapeado no padrão rest
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Maria","123","email","123");
		return ResponseEntity.ok().body(u);
	}
}
