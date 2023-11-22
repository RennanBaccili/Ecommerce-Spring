package com.bodyup.ecommerce.model.pk;

import java.io.Serializable;
import java.util.Objects;

import com.bodyup.ecommerce.model.Clother;
import com.bodyup.ecommerce.model.Order;
import com.bodyup.ecommerce.model.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// classe auxilar de chave primaira composta
//usar essa anotation nesse caso
@Embeddable
public class OrderItemPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "clother_id")
	 private Clother clother;
	
	//getters e setters
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	//nesse caso order e product terão hashcode equals
	
	public Clother getClother() {
		return clother;
	}
	public void setClother(Clother clother) {
		this.clother = clother;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clother, order);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		return Objects.equals(clother, other.clother) && Objects.equals(order, other.order);
	}
	
	
}
