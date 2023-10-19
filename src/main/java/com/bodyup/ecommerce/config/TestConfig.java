package com.bodyup.ecommerce.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bodyup.ecommerce.model.User;
import com.bodyup.ecommerce.repositories.UserRepository;

//para avisar que essa classe é deconfiguração
@Configuration
@Profile("test")				//commandLineRunner é para iniciar essa app por aqui
public class TestConfig implements CommandLineRunner{
	
	//inversão de dependencia
	@Autowired
	private UserRepository userRepository;

	//inicio os usuarios no banco de dados junto com a aplicacao
	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
	
		userRepository.saveAll(Arrays.asList(u1,u2));
	}
}
