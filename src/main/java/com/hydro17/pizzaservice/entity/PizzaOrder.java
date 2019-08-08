package com.hydro17.pizzaservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hydro17.pizzaservice.enums.OrderStatus;
import com.hydro17.pizzaservice.enums.PizzaSize;

@Entity
@Table(name="pizza_order")
public class PizzaOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
//	@Column(name="ordering_date")
//	private Date orderingDate;
	
	@Column(name="order_status")
	private OrderStatus orderStatus;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="pizza_id")
	private Pizza pizza;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name="pizza_size")
	private PizzaSize pizzaSize;

	public PizzaOrder() {}

	public PizzaOrder(OrderStatus orderStatus, int quantity, Customer customer, Pizza pizza, PizzaSize pizzaSize) {
		this.orderStatus = orderStatus;
		this.quantity = quantity;
		this.customer = customer;
		this.pizza = pizza;
		this.pizzaSize = pizzaSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	@Override
	public String toString() {
		return "PizzaOrder [id=" + id + ", orderStatus=" + orderStatus + ", quantity=" + quantity + ", customer="
				+ customer + ", pizza=" + pizza + ", pizzaSize=" + pizzaSize + "]";
	};
}
