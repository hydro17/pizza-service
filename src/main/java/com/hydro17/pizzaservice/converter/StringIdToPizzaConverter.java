package com.hydro17.pizzaservice.converter;

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
		
		return pizzaDAO.findById(pizzaIdAsInt);
	}
}
