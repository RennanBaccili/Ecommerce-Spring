package com.bodyup.ecommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	// exception de busca... vou enviar o id que desejo buscar e se eu não encontrar
	// eu retorno "Id não encontrado"
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}
}
