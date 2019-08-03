package com.hydro17.pizzaservice.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hydro17.pizzaservice.entity.Ingredient;

@Repository
public class IngredientDAOImpl implements IngredientDAO {
	
	@Autowired
	EntityManager em;
	
	@Override
	@Transactional
	public List<Ingredient> findAll() {
		List<Ingredient> ingredients = em.createQuery("from Ingredient", Ingredient.class).getResultList();
		return ingredients;
	}

	@Override
	@Transactional
	public Ingredient findById(int ingredientId) {
		Ingredient ingredient = em.find(Ingredient.class, ingredientId);
		return ingredient;
	}

	@Override
	@Transactional
	public void save(Ingredient ingredient) {
		em.merge(ingredient);
	}

	@Override
	@Transactional
	public void deleteById(int ingredientId) throws DataIntegrityViolationException {
		Ingredient ingredient = em.find(Ingredient.class, ingredientId);
		em.remove(ingredient);
	}
}
