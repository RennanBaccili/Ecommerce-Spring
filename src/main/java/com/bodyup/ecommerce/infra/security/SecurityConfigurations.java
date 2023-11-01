package com.bodyup.ecommerce.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//duas anotações para libararem a configuração do spring security
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	private SecurityFilter securityFilter;
	
	// representa corrente de filtro de segurança
	// corrente de filtros que eu vou aplicar na aplicação metodos de validação
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf -> csrf.disable()) // autenticação stateless, cria um token e valida se o taken ainda está valido
				.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // autenticação stateless, cria um token e valida se o taken ainda está valido
				//Configurando autenticação
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
						.requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
						.anyRequest().authenticated()
						)
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	// Configuração do AuthenticationManager
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

    // utiliza um algoritmo de hash para salvar os dados de forma criptografadas no banco de dados
    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
