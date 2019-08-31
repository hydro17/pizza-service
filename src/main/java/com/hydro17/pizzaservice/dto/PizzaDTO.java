package com.hydro17.pizzaservice.dto;

public class PizzaDTO {
	private int id;
	private String name;
	private String ingredients;
	private double smallPizzaPrice;
	private double mediumPizzaPrice;
	private double bigPizzaPrice;
	
	public PizzaDTO(int id, String name, String ingredients, double smallPizzaPrice, double mediumPizzaPrice,
			double bigPizzaPrice) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.smallPizzaPrice = smallPizzaPrice;
		this.mediumPizzaPrice = mediumPizzaPrice;
		this.bigPizzaPrice = bigPizzaPrice;
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

	public double getSmallPizzaPrice() {
		return smallPizzaPrice;
	}

	public double getMediumPizzaPrice() {
		return mediumPizzaPrice;
	}

	public double getBigPizzaPrice() {
		return bigPizzaPrice;
	}
	
}
