package com.bodyup.ecommerce.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodyup.ecommerce.model.Category;
import com.bodyup.ecommerce.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		 return repository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> category = repository.findById(id);
		return category.get();
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
