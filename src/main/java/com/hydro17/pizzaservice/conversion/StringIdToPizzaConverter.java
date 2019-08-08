package com.hydro17.pizzaservice.conversion;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.dao.PizzaDAO;
import com.hydro17.pizzaservice.entity.Pizza;

@Component
public class StringIdToPizzaConverter implements Converter<String, Pizza> {

	@Autowired
	private PizzaDAO pizzaDAO;
	
	@Override
	public Pizza convert(String pizzaId) {
		
		int pizzaIdAsInt = Integer.parseInt(pizzaId);
		
		return getPizzaBy(pizza -> pizza.getId() == pizzaIdAsInt);
	}

	private Pizza getPizzaBy(Predicate<Pizza> isItRequiredPizza) {
		
		List<Pizza> pizzas = pizzaDAO.findAll();
		
		for (Pizza pizza : pizzas) {
			if (isItRequiredPizza.test(pizza)) return pizza;
		}
		
		return null;
	}
}
