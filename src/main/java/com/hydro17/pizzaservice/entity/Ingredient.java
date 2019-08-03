package com.hydro17.pizzaservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="ingredient")
public class Ingredient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Size(min=2, max=20, message="Name must be between 2 and 20 characters")
	@Column(name="name")
	private String name;
	
	@Min(0)
	@Column(name="price")
	private double price;

	@Min(value=0, message="Please enter an initial number of the ingredient (0-20)")
	@Max(value=20, message="Please enter an initial number of the ingredient (0-20)")
	@Column(name="stock")
	private int stock;

	public Ingredient() {}
	
	public Ingredient(String name, double price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

//	@Override
//	public String toString() {
//		return "Ingredient [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
//	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
