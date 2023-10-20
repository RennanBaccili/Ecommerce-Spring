package com.bodyup.ecommerce.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	
	/*
	 *  Set pois o Set representa um conjunto
	 *	então vai garantir que eu não tenha mais de um produto com mais de uma ocorrencia na mesma categoria
	 *	o mesmo produtor não pode ter a mesma categoria mais deu ma vez
	 * */

	@ManyToMany
	@JoinTable( 
	name = "tb_product_category", // crio o nome da tabela que vai fazer a relação
	joinColumns = @JoinColumn(name= "product_id"), //chave estrangeira do produto
	inverseJoinColumns = @JoinColumn(name="category_id"))//chave estrangeira da categoria
	// vou falar qual vai ser o nome da tabela e quais serão as chaves estrangeiras
	private Set<Category> categories = new HashSet<>();
	
	//estanciar para a coleção não começar valendo nulla e sim vazia
	// Set é uma interface e o hashSet é correspondente a essa interface
	
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();
	// a partir da OrderItens vou pegar o acesso ao Orders
	
	public Product() {
		super();
	}
	// Como a coleção ja está seno estanciada acima não é necessario instanciar novamente no construtor
	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	//com o metodo GetOrders podemos pegar os orders
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		for (OrderItem x:items) {
			set.add(x.getOrder());
		}
		return set;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
}
