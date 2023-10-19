package com.bodyup.ecommerce.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bodyup.ecommerce.model.Category;
import com.bodyup.ecommerce.model.Order;
import com.bodyup.ecommerce.model.OrderItem;
import com.bodyup.ecommerce.model.Product;
import com.bodyup.ecommerce.model.User;
import com.bodyup.ecommerce.model.enums.OrderStatus;
import com.bodyup.ecommerce.repositories.CategoryRepository;
import com.bodyup.ecommerce.repositories.OrderItemRepository;
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

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	//inicio os usuarios no banco de dados junto com a aplicacao
	@Override
	public void run(String... args) throws Exception {
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "A");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "S");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "S");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "A");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		
		Category c1 = new Category(null, "Electronics");
		Category c2 = new Category(null, "Books");
		Category c3 = new Category(null, "Computers");
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		categoryRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		p1.getCategories().add(c2);
		p2.getCategories().add(c3);
		p2.getCategories().add(c1);
		p3.getCategories().add(c3);
		p4.getCategories().add(c3);
		p5.getCategories().add(c2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		// faz a associação altomatica, ao passar o user na instanciação
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.CANCELED, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.DELIVERED, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.SHIPPED, u1);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3));
	}
}
