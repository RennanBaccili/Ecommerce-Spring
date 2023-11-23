package com.bodyup.ecommerce.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodyup.ecommerce.cors.dto.ClotherDTO;
import com.bodyup.ecommerce.services.ClotherService;



//controlador da camada rest
@RestController
@RequestMapping(value= "/clother")
public class ClotherController {

	@Autowired
	private ClotherService service;
	//test
	
	//mapeado no padrão rest
	@GetMapping
	public ResponseEntity<List<ClotherDTO>> findAll(){
		List<ClotherDTO> u = service.findAll();
		return ResponseEntity.ok().body(u);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<ClotherDTO>> findById(@PathVariable Long id){
		var u = service.findById(id);
		return ResponseEntity.ok().body(u);
	}

	
}
