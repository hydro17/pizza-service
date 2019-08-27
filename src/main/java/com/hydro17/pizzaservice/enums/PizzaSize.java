package com.hydro17.pizzaservice.enums;

public enum PizzaSize {
	SMALL("mała"), MEDIUM("średnia"), LARGE("duża");
	
	private String polishName;
	
	private PizzaSize(String polishName) {
		this.polishName = polishName;
	}
	
	public String getPolishName() {
		return polishName;
	}
}
