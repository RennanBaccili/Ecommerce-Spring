package com.bodyup.ecommerce.model;

import com.bodyup.ecommerce.model.enums.ProductSize;

public class Clothes extends Product{

	private static final long serialVersionUID = 1L;
	
	private ProductSize size;

	public Clothes() {
		super();
	}

	public Clothes(Long id, String name, String description, Double price, String imgUrl,ProductSize size) {
		super(id, name, description, price, imgUrl);
		// TODO Auto-generated constructor stub
		this.size=size;
	}

	public ProductSize getSize() {
		return size;
	}

	public void setSize(ProductSize size) {
		this.size = size;
	}

}
