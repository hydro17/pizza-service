package com.hydro17.pizzaservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydro17.pizzaservice.dao.IngredientDAO;
import com.hydro17.pizzaservice.dao.PizzaDAO;
import com.hydro17.pizzaservice.dto.PizzaDTO;
import com.hydro17.pizzaservice.entity.Ingredient;
import com.hydro17.pizzaservice.entity.Pizza;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired
	private PizzaDAO pizzaDAO;
	
	@Autowired
	private IngredientDAO ingredientDAO;

	@GetMapping("/list")
	public String showPizzas(Model model) {
		
		List<Pizza> pizzas = pizzaDAO.findAll();
		List<PizzaDTO> pizzaDTOs = new ArrayList<>();
		
		pizzas.forEach(pizza -> {
			List<String> ingredientsAsString = new ArrayList<>();
			
			pizza.getIngredients().forEach(ingr -> {
				ingredientsAsString.add(ingr.getName()); 
			});
			
			String allIngredientsAsString = String.join(", ", ingredientsAsString);
			
			double pizzaPrice = 0;
			
			for (Ingredient ingr : pizza.getIngredients()) {
				pizzaPrice += ingr.getPrice();
			}

			pizzaDTOs.add(new PizzaDTO(pizza.getId(), pizza.getName(), allIngredientsAsString, pizzaPrice));
		});
		
		model.addAttribute("pizzaDTOs", pizzaDTOs);
		
		return "pizzas/list-pizzas";
	}
	
	@GetMapping("/add")
	public String showAddPizzaForm(Model model) {
		
		model.addAttribute("allIngredients", ingredientDAO.findAll());
		model.addAttribute("pizza", new Pizza());
		
		return "pizzas/add-or-update-pizza-form";
	}
	
	@PostMapping("/add")
	public String savePizza(@ModelAttribute Pizza pizza) {

		System.out.println(">>> INGREDIENTS: " + pizza.getIngredients());
		
		pizza.setId(0);
		pizzaDAO.save(pizza);
		
		return "redirect:/pizzas/list";
	}
	
	@GetMapping("/update/{pizzaId}")
	public String showUpdatePizzaForm(@PathVariable int pizzaId, Model model) {
		
		model.addAttribute("allIngredients", ingredientDAO.findAll());
		model.addAttribute("pizza", pizzaDAO.findById(pizzaId));
		
		return "pizzas/add-or-update-pizza-form";
	}

	@PostMapping("/update")
	public String updatePizza(@ModelAttribute Pizza pizza) {
		
		pizzaDAO.save(pizza);
		
		return "redirect:/pizzas/list";
	}
	
	@GetMapping("/delete/{pizzaId}")
	public String deleteById(@PathVariable int pizzaId) {
		
		pizzaDAO.deleteById(pizzaId);
		
		return "redirect:/pizzas/list";
	}
}
