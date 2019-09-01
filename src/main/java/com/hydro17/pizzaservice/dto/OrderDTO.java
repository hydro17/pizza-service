package com.hydro17.pizzaservice.dto;

import java.time.LocalDateTime;

import com.hydro17.pizzaservice.entity.Pizza;
import com.hydro17.pizzaservice.enums.PizzaOrderStatus;
import com.hydro17.pizzaservice.enums.PizzaSize;

public class OrderDTO {

	private int id;
	private LocalDateTime orderDate;
	private PizzaOrderStatus status;
	private Pizza pizza;
	private PizzaSize pizzaSize;
	private int quantity;
	private double price;
	
	public OrderDTO() {}
	
	public OrderDTO(int id, LocalDateTime orderDate, PizzaOrderStatus status, Pizza pizza, PizzaSize pizzaSize, int quantity,
			double price) {
		this.id = id;
		this.orderDate = orderDate;
		this.status = status;
		this.pizza = pizza;
		this.pizzaSize = pizzaSize;
		this.quantity = quantity;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public PizzaOrderStatus getStatus() {
		return status;
	}

	public void setStatus(PizzaOrderStatus status) {
		this.status = status;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", orderDate=" + orderDate + ", status=" + status + ", pizza=" + pizza + ", pizzaSize="
				+ pizzaSize + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
