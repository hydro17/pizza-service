package com.hydro17.pizzaservice.util;

import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.entity.Ingredient;
import com.hydro17.pizzaservice.entity.Pizza;

@Component
public class PizzaUtils {

	public double calculateSmallPizzaPrice(Pizza pizza) {
		
		double pizzaPrice = 0.0;
		
		for (Ingredient ingr : pizza.getIngredients()) {
			pizzaPrice += ingr.getPrice();
		}
		
		return pizzaPrice;
	}
	
}
