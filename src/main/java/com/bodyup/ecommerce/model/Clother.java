package com.bodyup.ecommerce.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.bodyup.ecommerce.model.enums.ClotherSize;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_clother")
public class Clother extends Product{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "status", columnDefinition = "VARCHAR(10) DEFAULT 'ativo'")
	private String status = "ativo";
	
	 // Novo campo para representar as quantidades disponíveis para cada tamanho
    @ElementCollection
    @MapKeyColumn(name = "size")
    @CollectionTable(name = "clother_sizes", joinColumns = @JoinColumn(name = "clother_id"))
    @Column(name = "quantity")
    private Map<ClotherSize, Integer> sizesQuantities = new HashMap<>();
	
	
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

	 public Clother(Long id, String name, String description, Double price, String imgUrl, Map<ClotherSize, Integer> sizesQuantities) {
	        super(id, name, description, price, imgUrl);
	        
	        this.sizesQuantities = (sizesQuantities != null) ? sizesQuantities : getDefaultSizesQuantities();
	    }
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	//metodos Map
	
  public Map<ClotherSize, Integer> getSizesQuantities() {
        return sizesQuantities;
    }

    public void setSizesQuantities(Map<ClotherSize, Integer> sizesQuantities) {
        this.sizesQuantities = sizesQuantities;
    }

    public void addQuantityForSize(ClotherSize size, int quantity) {
        sizesQuantities.put(size, quantity);
    }
    
 // Método para obter quantidades padrão
    private Map<ClotherSize, Integer> getDefaultSizesQuantities() {
        Map<ClotherSize, Integer> defaultQuantities = new HashMap<>();
        for (ClotherSize size : ClotherSize.values()) {
            defaultQuantities.put(size, 0);
        }
        return defaultQuantities;
    }
    
    //metodos set
    
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
	
	// Método para marcar o registro como excluído
	public void delete() {
	    this.status = "excluído";
	}

	// Método para restaurar o registro excluído
	public void restore() {
	    this.status = "ativo";
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
