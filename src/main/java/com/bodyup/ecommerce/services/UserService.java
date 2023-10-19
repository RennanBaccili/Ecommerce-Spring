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
	private UserRepository userRepository;
	
	public List<User> findAll(){
		 return userRepository.findAll();
	}

	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	
	public User insertUser(User obj) {
		return userRepository.save(obj);
	}
	
	
}
