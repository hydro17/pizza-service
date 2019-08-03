package com.hydro17.pizzaservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hydro17.pizzaservice.conversion.StringIdToIngredientConverter;
import com.hydro17.pizzaservice.dao.IngredientDAO;
import com.hydro17.pizzaservice.entity.Ingredient;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientDAO ingredientDAO;
	
	@Autowired
	private StringIdToIngredientConverter stringIdToIngredientConverter;
	
	@GetMapping("/all")
	public String listAll(Model model) {
		
		List<Ingredient> ingredients = ingredientDAO.findAll();
		model.addAttribute("ingredients", ingredients);
		
		return "list-all-ingredients";
	}
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		
		return "add-ingredient-form";
	}
	
	@PostMapping("/add")
	public String saveIngredient(@ModelAttribute Ingredient ingredient) {
		
		ingredient.setId(0);
		ingredientDAO.save(ingredient);
		
		stringIdToIngredientConverter.setIngredients(ingredientDAO.findAll());
		
		return "redirect:/ingredients/all";
	}
	
	@GetMapping("/update/{ingredientId}")
//	@GetMapping("/update")
	public String showUpdateForm(@PathVariable int ingredientId, Model model) {
//	public String showUpdateForm(@RequestParam("id") int ingredientId, Model model) {
		
		Ingredient ingredient = ingredientDAO.findById(ingredientId); 
		model.addAttribute("ingredient", ingredient);
		
		return "update-ingredient-form";
	}
	
	@PostMapping("/update")
	public String updateIngredient(@ModelAttribute Ingredient ingredient) {
		
		ingredientDAO.save(ingredient);
		
		return "redirect:/ingredients/all";
	}
	
	@GetMapping("/delete/{ingredientId}")
	public String deleteById(@PathVariable int ingredientId) {
		
		try {
			ingredientDAO.deleteById(ingredientId);
			
			stringIdToIngredientConverter.setIngredients(ingredientDAO.findAll());
		} catch (Exception e) {
			System.out.println(">>>>>> [CONTROLLER] Cannot delete ingredient: " + ingredientId);
			e.printStackTrace();
		}
		
		return "redirect:/ingredients/all";
	}
}
