package com.bodyup.ecommerce.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

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

@Entity
@Table(name="tab_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	//defino a chave primaria, com valor sera gerado automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataNasc;
	private String cpf;
	private String email;
	private String password;
	
	
	//como essa lista de pedidos é uma coleção ela ja vai ser instanciada
	// um usuario pode ter varias ordens de serviço
	
	@OneToMany(mappedBy="client")
	@JsonIgnore
	private List<Order> orders = new ArrayList<>();
	//construtores
	public User() {
	}

	public User(Long id, String name,String dataNasc, String cpf, String email, String password) throws ParseException {
		this.id = id;
		this.name = name;
		convertData(dataNasc);
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		
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

	
	public Calendar getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Calendar dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	public void convertData(String dataNasc) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.dataNasc = Calendar.getInstance();
		this.dataNasc.setTime(dateFormat.parse(dataNasc));
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
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
		return Objects.equals(cpf, other.cpf);
	}
	

	
	
	
}
