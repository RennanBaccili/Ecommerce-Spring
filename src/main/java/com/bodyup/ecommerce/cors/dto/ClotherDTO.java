package com.bodyup.ecommerce.cors.dto;

import com.bodyup.ecommerce.model.Clother;
import com.bodyup.ecommerce.model.enums.ClotherSize;

public record ClotherDTO(Long id, String name, String description, Double price, String imgUrl, ClotherSize size) {

	public ClotherDTO(Clother clother) {
		this(clother.getId(),
                clother.getName(),
                clother.getDescription(),
                clother.getPrice(),
                clother.getImgUrl(),
                clother.getSize());
	}
}
