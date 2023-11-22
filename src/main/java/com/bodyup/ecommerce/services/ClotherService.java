package com.bodyup.ecommerce.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodyup.ecommerce.model.Clother;
import com.bodyup.ecommerce.repositories.ClotherRepository;

@Service
public class ClotherService {
	
	@Autowired
	private ClotherRepository repository;
	
	public List<Clother> findAll(){
		 return repository.findAll();
	}

	public Clother findById(Long id) {
		Optional<Clother> product = repository.findById(id);
		return product.get();
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
