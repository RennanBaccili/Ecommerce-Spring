package com.bodyup.ecommerce.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodyup.ecommerce.model.User;
import com.bodyup.ecommerce.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		 return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.get();
	}
	
	public User insertUser(User obj) {
		return repository.save(obj);
	}
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		// getReference observa o id passado... o metodo updateData defini quais atributos serão alterados
		User entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	// datas que serão atualizadas
	public void updateData(User entity, User obj) {
		//altera o valor dos entitys
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setCpf(obj.getCpf());
	}
}
