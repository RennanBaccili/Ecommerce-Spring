package com.bodyup.ecommerce.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bodyup.ecommerce.model.Category;
import com.bodyup.ecommerce.model.Order;
import com.bodyup.ecommerce.model.Product;
import com.bodyup.ecommerce.model.User;
import com.bodyup.ecommerce.model.enums.OrderStatus;
import com.bodyup.ecommerce.repositories.CategoryRepository;
import com.bodyup.ecommerce.repositories.OrderRepository;
import com.bodyup.ecommerce.repositories.ProductRepository;
import com.bodyup.ecommerce.repositories.UserRepository;

//para avisar que essa classe é deconfiguração
@Configuration
@Profile("test")				//commandLineRunner é para iniciar essa app por aqui
public class TestConfig implements CommandLineRunner{
	
	//inversão de dependencia
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	//inicio os usuarios no banco de dados junto com a aplicacao
	@Override
	public void run(String... args) throws Exception {
		
		Product p1 = new Product(null,"Camisa branca","Essa camisa é bela", 60.0, "cammisaurl");
		Product p2 = new Product(null,"Camisa branca","Essa camisa é bela", 50.0, "cammisaurl");
		Product p3 = new Product(null,"Camisa branca","Essa camisa é bela", 30.0, "cammisaurl");
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Category c1 = new Category(null,"Camisa Larga");
		Category c2 = new Category(null,"Manga Comprida");
		
		categoryRepository.saveAll(Arrays.asList(c1,c2));
		
		
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		// faz a associação altomatica, ao passar o user na instanciação
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.CANCELED, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.DELIVERED, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.PAID, u1);
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		
	}
}
