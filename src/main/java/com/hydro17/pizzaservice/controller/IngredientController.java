package com.hydro17.pizzaservice.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydro17.pizzaservice.converter.StringIdToIngredientConverter;
import com.hydro17.pizzaservice.dao.IngredientDAO;
import com.hydro17.pizzaservice.entity.Ingredient;
import com.hydro17.pizzaservice.model.ConstraintViolation;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientDAO ingredientDAO;
	
	@Autowired
	private StringIdToIngredientConverter stringIdToIngredientConverter;
	
	private ConstraintViolation ingredientConstraintViolation;
	
	@GetMapping("/list")
	public String listAll(Model model) {
		
		List<Ingredient> ingredients = ingredientDAO.findAll();
		Collections.sort(ingredients);
		
		model.addAttribute("ingredientConstraintViolation", this.ingredientConstraintViolation);
		this.ingredientConstraintViolation = null;
		
		model.addAttribute("ingredients", ingredients);
		
		return "ingredients/list-ingredients";
	}
	
	@GetMapping("/add")
	public String showAddIngredientForm(Model model) {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setPrice(2.0);
		ingredient.setStock(1);
		
		model.addAttribute("ingredient", ingredient);
		
		return "ingredients/add-or-update-ingredient-form";
	}
	
	@PostMapping("/add")
	public String saveIngredient(@Valid @ModelAttribute Ingredient ingredient, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "ingredients/add-or-update-ingredient-form";
		}
		
		ingredient.setId(0);
		ingredientDAO.save(ingredient);
		
		//Update list of ingredients in StringIdToIngredientConverter
		stringIdToIngredientConverter.setIngredients(ingredientDAO.findAll());
		
		return "redirect:/ingredients/list";
	}
	
	@GetMapping("/update/{ingredientId}")
	public String showUpdateIngredientForm(@PathVariable int ingredientId, Model model) {
		
		Ingredient ingredient = ingredientDAO.findById(ingredientId); 
		model.addAttribute("ingredient", ingredient);
		
		return "ingredients/add-or-update-ingredient-form";
	}
	
	@PostMapping("/update")
	public String updateIngredient(@Valid @ModelAttribute Ingredient ingredient, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "ingredients/add-or-update-ingredient-form";
		}
		
		ingredientDAO.save(ingredient);
		
		//Update list of ingredients in StringIdToIngredientConverter
		stringIdToIngredientConverter.setIngredients(ingredientDAO.findAll());
		
		return "redirect:/ingredients/list";
	}
	
	@GetMapping("/delete/{ingredientId}")
	public String deleteById(@PathVariable int ingredientId) {
		
		try {
			// Ingredient with given ingredientId could have been removed in the meantime
			if (ingredientDAO.findById(ingredientId) != null) { 
				ingredientDAO.deleteById(ingredientId);
			}
			
			//Update list of ingredients in StringIdToIngredientConverter
			stringIdToIngredientConverter.setIngredients(ingredientDAO.findAll());
			
		} catch (DataIntegrityViolationException e) {
			
			System.out.println(">>> [source: CONTROLLER] Cannot delete the ingredient: " + ingredientId);
			
			this.ingredientConstraintViolation = new ConstraintViolation(ingredientId, "Nie może być usunięty, jest używany jako składnik pizzy.");
		
		} catch (Exception e) {
			
			System.out.println(">>> [source: CONTROLLER] Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/ingredients/list";
	}
}
