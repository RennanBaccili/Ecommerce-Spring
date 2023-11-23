package com.bodyup.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bodyup.ecommerce.model.Clother;

public interface ClotherRepository extends JpaRepository<Clother, Long>{

	 // Método para obter todos os registros ativos
    List<Clother> findByStatus(String status);
    
    List<Clother> findByStatusAndStatusNot(String status, String statusNot);
}
