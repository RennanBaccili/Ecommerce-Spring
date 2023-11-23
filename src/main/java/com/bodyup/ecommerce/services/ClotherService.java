package com.bodyup.ecommerce.services;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodyup.ecommerce.cors.dto.ClotherDTO;
import com.bodyup.ecommerce.model.Clother;
import com.bodyup.ecommerce.model.enums.ClotherSize;
import com.bodyup.ecommerce.repositories.ClotherRepository;

@Service
public class ClotherService {
	
	@Autowired
	private ClotherRepository repository;
	

	public List<ClotherDTO> findAll() {
	       List<Clother> clotherList = repository.findAll();
	       return clotherList.stream()
	              .map(ClotherDTO::new)
	              .collect(Collectors.toList());
	    }
	
	public Optional<ClotherDTO> findById(Long id) {
		return repository.findById(id).map(ClotherDTO::new);
	}
	
	public void inserClother(ClotherDTO dados) {
	    Map<ClotherSize, Integer> sizesQuantities = dados.sizesQuantities();

		repository.save(new Clother(dados.id(),
				dados.name(),
				dados.description(),
				dados.price(),
				dados.imgUrl(),
				sizesQuantities));
	}
//	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
