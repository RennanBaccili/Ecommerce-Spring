package com.bodyup.ecommerce.model;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bodyup.ecommerce.model.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//implementa classe serializable

// user details é uma interrface que sera implementada

@Entity
@Table(name="tab_user")
public class User implements Serializable,UserDetails {

	private static final long serialVersionUID = 1L;
	//defino a chave primaria, com valor sera gerado automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Calendar date;
	
	private String cpf;
	private String email;
	private String password;
	
	private UserRoles role;
	
	
	//como essa lista de pedidos é uma coleção ela ja vai ser instanciada
	// um usuario pode ter varias ordens de serviço
	
	@OneToMany(mappedBy="client")
	@JsonIgnore
	private List<Order> orders = new ArrayList<>();
	//construtores
	public User() {
	}

	public User(String email, String password,UserRoles role) {
		this.email=email;
		this.password=password;
		this.role=role;
	}
	
	public User(Long id, String name,String data, String cpf, String email, String password, UserRoles role) throws ParseException {
		this.id = id;
		this.name = name;
		setDate(data);
		this.cpf = cpf;
		this.email = email;
		this.password = password;
	    this.role = UserRoles.USER;
	}
	
	//getters setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Calendar getDate() {
		return date;
	}
	
	public void setDate(String dataNasc) throws ParseException {
		LocalDate localDate = LocalDate.parse(dataNasc, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth()); // Mês é indexado de 0 a 11

        this.date = calendar;
	}
		
	public List<Order> getOrders() {
		return orders;
	}
	
	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}

	

    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

//	UserDetails
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		// rola admin, tem todos os acessos, o user tem só de userr
    	if(this.role == UserRoles.ADMIN)
    		return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    	else
    		return List.of(new SimpleGrantedAuthority("Role_USER"));
        // Se precisar de autorizações, pode implementar essa lógica aqui
        // Por exemplo, se os usuários têm diferentes papéis (roles)
    }

    @Override
    public String getUsername() {
        return email;// Supondo que o e-mail seja o nome de usuário
    }
    
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
