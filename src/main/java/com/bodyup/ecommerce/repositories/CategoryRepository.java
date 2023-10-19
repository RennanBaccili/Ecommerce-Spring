package com.bodyup.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bodyup.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
