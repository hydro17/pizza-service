package com.hydro17.pizzaservice.enums;

import com.hydro17.pizzaservice.globals.PizzaServiceConstants;

public enum PizzaSize {
	SMALL("mała", PizzaServiceConstants.smallPizzaMultiplier), 
	MEDIUM("średnia", PizzaServiceConstants.mediumPizzaMultiplier), 
	LARGE("duża", PizzaServiceConstants.bigPizzaMultiplier);
	
	private String polishName;
	private double priceMultiplier;
	
	private PizzaSize(String polishName, double priceMultiplier) {
		this.polishName = polishName;
		this.priceMultiplier = priceMultiplier;
	}
	
	public String getPolishName() {
		return polishName;
	}

	public double getPriceMultiplier() {
		return priceMultiplier;
	}
}
