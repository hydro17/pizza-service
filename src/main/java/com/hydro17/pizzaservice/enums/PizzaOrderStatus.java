package com.hydro17.pizzaservice.enums;

public enum PizzaOrderStatus {
	ORDERED("zamówione"), IN_PREPARATION("w przygotowaniu"), PREPARED("gotowe"), DELIVERED("dostarczone"), PICKED_UP("odebrane");
	
	private String polishName;
	
	private PizzaOrderStatus(String polishName) {
		this.polishName = polishName;
	}

	public String getPolishName() {
		return polishName;
	}
}
