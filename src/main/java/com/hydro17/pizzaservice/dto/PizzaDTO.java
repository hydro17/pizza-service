package com.hydro17.pizzaservice.dto;

public class PizzaDTO {
	private int id;
	private String name;
	private String ingredients;
	private double price;
	
	public PizzaDTO(int id, String name, String ingredients, double price) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public double getPrice() {
		return price;
	}
}
