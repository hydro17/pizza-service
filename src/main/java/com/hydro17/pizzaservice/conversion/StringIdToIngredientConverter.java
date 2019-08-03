package com.hydro17.pizzaservice.conversion;

import java.util.List;
import java.util.function.Predicate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.dao.IngredientDAO;
import com.hydro17.pizzaservice.entity.Ingredient;

@Component
public class StringIdToIngredientConverter implements Converter<String, Ingredient> {

	private List<Ingredient> ingredients;

	@Autowired
	public StringIdToIngredientConverter(IngredientDAO ingredientDAO) {
		this.ingredients = ingredientDAO.findAll();
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public Ingredient convert(String ingredientId) {
		
		int ingredientIdAsInt = Integer.parseInt(ingredientId);
		Ingredient ingredient = getIngredientBy(ingr -> ingr.getId() == ingredientIdAsInt);
		
		return ingredient;
	}

	private Ingredient getIngredientBy(Predicate<Ingredient> isItRequiredIngredient) {
		
		for (Ingredient ingredient : this.ingredients) {
			if (isItRequiredIngredient.test(ingredient)) return ingredient;
		}
		
		return null;
	}
}
