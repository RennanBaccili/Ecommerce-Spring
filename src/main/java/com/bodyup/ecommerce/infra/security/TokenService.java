package com.bodyup.ecommerce.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bodyup.ecommerce.model.User;

@Service 
public class TokenService {
	
	//valor definido no propities para a secret
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		try {
			//Primeiro define o algoritimo de token, ele recebe por parametro uma secret
			//A Secret serve para gerar hashs diferentes, um parametro para adicionar segurança
			//Como uma chave de segurança
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create() // aqui geramos o token
					.withIssuer("auth-api") //Issuor é o emissor que criou a api
					.withSubject(user.getEmail()) //Subject é o usuario que está recebendo o token
					.withExpiresAt(genExpirationDate())// tempo de experiação do token
					.sign(algorithm); //assinatura e geração final
			
			return token;
			
		} catch(JWTCreationException exception){
			throw new RuntimeException("Error while generating toke",exception);
		}

	}
	
	public String validateToken(String token) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)// descptografia do token
					.getSubject(); // e pego o dado
		} catch (JWTVerificationException exception) {
			return "";
		}
	}
	
	private Instant genExpirationDate() { //2 horas
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
