package com.hydro17.pizzaservice.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.entity.Pizza;

@Component
public class PizzaToStringIdConverter implements Converter<Pizza, String> {

	@Override
	public String convert(Pizza pizza) {
		
		return String.valueOf(pizza.getId());
	}

}
