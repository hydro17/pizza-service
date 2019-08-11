package com.hydro17.pizzaservice.controller;

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

import com.hydro17.pizzaservice.conversion.StringIdToIngredientConverter;
import com.hydro17.pizzaservice.dao.IngredientDAO;
import com.hydro17.pizzaservice.entity.Ingredient;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientDAO ingredientDAO;
	
	private class IngredientComment {
		private int id;
		private String comment;
		
		public IngredientComment(int ingredientId, String comment) {
			this.id = ingredientId;
			this.comment = comment;
		}

		public int getId() {
			return id;
		}

		public String getComment() {
			return comment;
		}
	}
	
	private IngredientComment ingredientComment;
	
	@GetMapping("/list")
	public String listAll(Model model) {
		
		List<Ingredient> ingredients = ingredientDAO.findAll();
		
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("ingredientComment", this.ingredientComment);
		
		this.ingredientComment = null;
		
		return "ingredients/list-ingredients";
	}
	
	@GetMapping("/add")
	public String showAddIngredientForm(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		
		return "ingredients/add-or-update-ingredient-form";
	}
	
	@PostMapping("/add")
	public String saveIngredient(@Valid @ModelAttribute Ingredient ingredient, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "ingredients/add-or-update-ingredient-form";
		}
		
		ingredient.setId(0);
		ingredientDAO.save(ingredient);
		
		return "redirect:/ingredients/list";
	}
	
	@GetMapping("/update/{ingredientId}")
//	@GetMapping("/update")
	public String showUpdateForm(@PathVariable int ingredientId, Model model) {
//	public String showUpdateForm(@RequestParam("id") int ingredientId, Model model) {
		
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
		
		return "redirect:/ingredients/list";
	}
	
	@GetMapping("/delete/{ingredientId}")
	public String deleteById(@PathVariable int ingredientId) {
		
		try {
			ingredientDAO.deleteById(ingredientId);
			
		} catch (DataIntegrityViolationException e) {
			
			System.out.println(">>> [source: CONTROLLER] Cannot delete the ingredient: " + ingredientId);
			
			this.ingredientComment = new IngredientComment(ingredientId, "Cannot be removed, is used");
		
		} catch (Exception e) {
			
			System.out.println(">>> [source: CONTROLLER] Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/ingredients/list";
	}
}
