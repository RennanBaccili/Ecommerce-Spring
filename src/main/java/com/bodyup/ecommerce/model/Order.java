package com.bodyup.ecommerce.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.bodyup.ecommerce.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	// defino a chave primaria, com valor sera gerado automaticamente
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	
	private Integer orderStatus;
	// seguindo conceito de que como essa classe vai ter apenas um usuario
	// e o usario vai ter vai ter varios ordens de pedido
	// essa classe recebe a chave estrangeira
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;
	// o que é enviado para  o servidor é código integer, convertido
	// pela função valueOf
	
	
	// a partir do mapeamento um para muitos, a classe pedido conhece os items dele
	@OneToMany(mappedBy="id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	//no caso do um para um, estou mapeando as entidades para terem o mesmo id
	// para isso é necessario o cascade
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;
	
	public Order() {
		super();
	}

	public Order(Long id, Instant moment,OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
		setOrderStatus(orderStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	//converto o numero passado para OrderStatus
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	//utilizo o getCode para pegar o codigo e adicionar a variavel inteira
	public void setOrderStatus(OrderStatus order) {
		if(order != null) {
			this.orderStatus = order.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}
