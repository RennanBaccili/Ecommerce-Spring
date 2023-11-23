package com.bodyup.ecommerce.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bodyup.ecommerce.model.Category;
import com.bodyup.ecommerce.model.Clother;
import com.bodyup.ecommerce.model.Order;
import com.bodyup.ecommerce.model.Payment;
import com.bodyup.ecommerce.model.User;
import com.bodyup.ecommerce.model.enums.ClotherSize;
import com.bodyup.ecommerce.model.enums.OrderStatus;
import com.bodyup.ecommerce.repositories.CategoryRepository;
import com.bodyup.ecommerce.repositories.ClotherRepository;
import com.bodyup.ecommerce.repositories.OrderItemRepository;
import com.bodyup.ecommerce.repositories.OrderRepository;
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
	private ClotherRepository clotherRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	//inicio os usuarios no banco de dados junto com a aplicacao
	@Override
	public void run(String... args) throws Exception {
		
		Map<ClotherSize, Integer> sizesQuantities = new HashMap<>();
		sizesQuantities.put(ClotherSize.P, 10);
		sizesQuantities.put(ClotherSize.M, 15);
		sizesQuantities.put(ClotherSize.G, 20);
		sizesQuantities.put(ClotherSize.GG, 30);

		Clother clother = new Clother(1L, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "", sizesQuantities);
		
		Category c1 = new Category(null, "Camiseta");

		categoryRepository.saveAll(Arrays.asList(c1));
		
		clother.getCategories().add(c1);
		
		clotherRepository.save(clother);
		
		User u1 = new User(null, "Nome1", "2023-10-04", "CPF1", "email1@example.com", "senha1");
		User u2 = new User(null,  "Nome2", "2023-10-05", "CPF2", "email2@example.com", "senha2");
		
		// faz a associação altomatica, ao passar o user na instanciação
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.DELIVERED, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.SHIPPED, u1);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		
		// pagamento é setado na sua ordem,
		Payment pay1 = new Payment(null,Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
	}
}
