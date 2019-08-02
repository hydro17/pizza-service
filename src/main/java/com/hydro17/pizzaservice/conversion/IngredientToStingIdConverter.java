package com.hydro17.pizzaservice.conversion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.dao.IngredientDAO;
import com.hydro17.pizzaservice.entity.Ingredient;

@Component
public class IngredientToStingIdConverter implements Converter<Ingredient, String> {

	@Override
	public String convert(Ingredient ingredient) {
		return String.valueOf(ingredient.getId());
	}
}
