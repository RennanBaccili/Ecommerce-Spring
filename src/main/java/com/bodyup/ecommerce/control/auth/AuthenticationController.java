package com.bodyup.ecommerce.control.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodyup.ecommerce.infra.security.TokenService;
import com.bodyup.ecommerce.model.User;
import com.bodyup.ecommerce.model.dto.AuthenticationDTO;
import com.bodyup.ecommerce.model.dto.LoginResponseDTO;
import com.bodyup.ecommerce.model.dto.RegisterDTO;
import com.bodyup.ecommerce.repositories.UserRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
        	// essa classe vem do spring security
        	var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        	//   aqui autentica o username token
        	var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User)auth.getPrincipal());
            // Supondo que o retorno seja uma String representando um token JWT ou uma mensagem
            
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException e) {
            // Aqui você trata a exceção de autenticação (pode ser uma exceção mais específica)
            // Pode ser um erro de credenciais inválidas, conta desativada, etc.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO("Falha na autenticação"));
        } catch (Exception e) {
            // Captura exceções gerais que não foram previstas
            // Este bloco trata todas as outras exceções não capturadas acima
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponseDTO("Erro interno no servidor"));
        }
}
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
	    if (repository.findByEmail(data.email()) != null) {
	        return ResponseEntity.badRequest().body("O e-mail fornecido já está em uso.");
	    }
	    // dessa forma pegamos o hash da senha do usuario
	    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

	    // Registrando a senha encriptada
	    User user = new User(data.email(), encryptedPassword, data.role());
	    // Salvando o novo usuário no repositório
	    repository.save(user);

	    return ResponseEntity.ok().build();
	}
}