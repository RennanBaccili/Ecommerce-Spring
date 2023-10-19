package com.bodyup.ecommerce.model.enums;

public enum OrderStatus {

	/*
	 * Primeiro foi criado a ordem serviço
	 * depois criamos o atributo code
	 * ValueOf para converter o codigo que sera passado para o servidor
	 * 
	 * */
	
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value: OrderStatus.values()) {
			if(value.getCode()==code) {
				return value;
			}
		}
		throw new IllegalArgumentException();
	}
}

