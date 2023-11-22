package com.bodyup.ecommerce.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.bodyup.ecommerce.model.enums.ClotherSize;
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
@Table(name="tab_clother")
public class Clother extends Product{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private ClotherSize size;
	
    // Relacionamento muitos para muitos com Category
    @ManyToMany
    @JoinTable(
            name = "tb_clother_category",  // Nome da tabela de junção para Clother e Category
            joinColumns = @JoinColumn(name = "clother_id"),  // Chave estrangeira para Clother
            inverseJoinColumns = @JoinColumn(name = "category_id"))  // Chave estrangeira para Category
    private Set<Category> categories = new HashSet<>();

    // Relacionamento um para muitos com OrderItem
    @OneToMany(mappedBy = "id.clother")  // Indicando a propriedade 'clother' na classe OrderItem como mapeamento inverso
    private Set<OrderItem> items = new HashSet<>();
	
	public Clother() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clother(Long id, String name, String description, Double price, String imgUrl, ClotherSize size) {
		super(id, name, description, price, imgUrl);
		this.size=size;
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public ClotherSize getSize() {
		return size;
	}

	public void setSize(ClotherSize size) {
		this.size = size;
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
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clother other = (Clother) obj;
		return Objects.equals(id, other.id);
	}
}
