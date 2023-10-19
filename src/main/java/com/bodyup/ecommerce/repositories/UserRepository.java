package com.bodyup.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bodyup.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
