package com.hydro17.pizzaservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hydro17.pizzaservice.dao.IngredientDAO;
import com.hydro17.pizzaservice.entity.Ingredient;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	IngredientDAO ingredientDAO;
	
	@GetMapping("/list-all")
	public String listAll(Model model) {
		
		List<Ingredient> ingredients = ingredientDAO.findAll();
		model.addAttribute("ingredients", ingredients);
		
		return "list-all-ingredients";
	}
	
//	@GetMapping("/list-one")
//	public String listById(@RequestParam("id") int ingredientID) {
//		
//	}
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		
		return "add-ingredient-form";
	}
	
	@PostMapping("/add")
	public String processAddForm(@ModelAttribute Ingredient ingredient) {
		
		ingredient.setId(0);
		ingredientDAO.save(ingredient);
		
		return "redirect:/ingredient/list-all";
	}
	
	@GetMapping("/update")
	public String showUpdateForm(@RequestParam("id") int ingredientId, Model model) {
		
		Ingredient ingredient = ingredientDAO.findById(ingredientId); 
		model.addAttribute("ingredient", ingredient);
		
		return "update-ingredient-form";
	}
	
	@PostMapping("/update")
	public String processUpdateForm(@ModelAttribute Ingredient ingredient) {
		
		ingredientDAO.save(ingredient);
		
		return "redirect:/ingredient/list-all-ingredients";
	}
	
	@GetMapping("/delete")
	public String deleteById(@RequestParam("id") int ingredientId) {
		
		ingredientDAO.deleteById(ingredientId);
		
		return "redirect:/ingredient/list-all";
	}
}
