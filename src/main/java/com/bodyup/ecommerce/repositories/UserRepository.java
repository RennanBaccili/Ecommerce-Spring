package com.bodyup.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.bodyup.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	//findBy Nome do login ou email
	UserDetails findByEmail(String email);
}
