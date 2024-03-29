package com.hydro17.pizzaservice.dao;

import java.util.List;

import com.hydro17.pizzaservice.entity.Ingredient;

public interface IngredientDAO {

	List<Ingredient> findAll();
	Ingredient findById(int ingredientId);
	void save(Ingredient ingredient);
	void deleteById(int ingredientId) throws Exception;
}
