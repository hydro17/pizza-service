package com.hydro17.pizzaservice.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.entity.Ingredient;

//@Component
public class IngredientToStringIdConverter implements Converter<Ingredient, String> {

	@Override
	public String convert(Ingredient ingredient) {
		
		return String.valueOf(ingredient.getId());
	}
}
