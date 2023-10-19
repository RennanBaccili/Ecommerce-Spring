package com.bodyup.ecommerce.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodyup.ecommerce.model.User;
import com.bodyup.ecommerce.services.UserService;



//controlador da camada rest
@RestController
@RequestMapping(value= "/users")
public class UserController {

	@Autowired
	private UserService userService;
	//test
	
	//mapeado no padrão rest
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> u = userService.findAll();
		return ResponseEntity.ok().body(u);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User u = userService.findById(id);
		return ResponseEntity.ok().body(u);
	}
}
