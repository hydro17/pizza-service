package com.hydro17.pizzaservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hydro17.pizzaservice.enums.PizzaOrderStatus;
import com.hydro17.pizzaservice.enums.PizzaSize;

@Entity
@Table(name="pizza_order")
public class PizzaOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="order_date")
	private LocalDateTime orderDate;
	
	@Column(name="order_status")
	private PizzaOrderStatus status;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="pizza_id")
	private Pizza pizza;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="pizza_size")
	private PizzaSize pizzaSize;

	public PizzaOrder() {}


	public PizzaOrder(LocalDateTime orderingDate, PizzaOrderStatus status, int quantity, Pizza pizza, User user,
			PizzaSize pizzaSize) {
		this.orderDate = orderingDate;
		this.status = status;
		this.quantity = quantity;
		this.pizza = pizza;
		this.user = user;
		this.pizzaSize = pizzaSize;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public PizzaOrderStatus getStatus() {
		return status;
	}

	public void setStatus(PizzaOrderStatus status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public PizzaSize getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(PizzaSize pizzaSize) {
		this.pizzaSize = pizzaSize;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "PizzaOrder [id=" + id + ", orderDate=" + orderDate + ", status=" + status + ", quantity="
				+ quantity + ", pizza=" + pizza + ", user=" + user + ", pizzaSize=" + pizzaSize + "]";
	}
}
