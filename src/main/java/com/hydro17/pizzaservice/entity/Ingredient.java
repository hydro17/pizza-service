package com.hydro17.pizzaservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="ingredient")
public class Ingredient {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Size(min=1, max=20, message="Nazwa powinna składać się z od 1 do 20 znaków")
	@Column(name="ingredient_name")
	private String ingredientName;
	
	@Min(value=0, message="Cena nie może być niższa niż 0 zł")
	@Column(name="price")
	private double price;

	@Min(value=0, message="Please enter an initial number of the ingredient (0-20)")
	@Max(value=20, message="Please enter an initial number of the ingredient (0-20)")
	@Column(name="stock")
	private int stock;
	
	public Ingredient() {}
	
	public Ingredient(String name, double price, int stock) {
		this.ingredientName = name;
		this.price = price;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String name) {
		this.ingredientName = name;
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
