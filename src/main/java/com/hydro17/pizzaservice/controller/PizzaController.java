package com.hydro17.pizzaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydro17.pizzaservice.dao.IngredientDAO;
import com.hydro17.pizzaservice.dao.PizzaDAO;
import com.hydro17.pizzaservice.entity.Pizza;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired
	private PizzaDAO pizzaDAO;
	
	@Autowired
	private IngredientDAO ingredientDAO;

	@GetMapping("/all")
	public String showPizzas(Model model) {
		
		model.addAttribute("pizzas", pizzaDAO.findAll());
		
		return "list-all-pizzas";
	}
	
	@GetMapping("/add")
	public String showAddPizzaForm(Model model) {
		
		model.addAttribute("allIngredients", ingredientDAO.findAll());
		model.addAttribute("pizza", new Pizza());
		
		return "add-pizza-form";
	}
	
	@PostMapping("/add")
	public String savePizza(@ModelAttribute Pizza pizza) {

		pizza.setId(0);
		pizzaDAO.save(pizza);
		
		return "redirect:/pizzas/all";
	}
	
	@GetMapping("/update/{pizzaId}")
	public String showUpdatePizzaForm(@PathVariable int pizzaId, Model model) {
		
		Pizza pizza = pizzaDAO.findById(pizzaId);
		model.addAttribute("pizza", pizza);
		
		return "update-pizza-form";
	}
	
	@PostMapping("/update")
	public String updatePizza(@ModelAttribute Pizza pizza) {
		
		pizzaDAO.save(pizza);
		
		return "redirect:/pizzas/all";
	}
	
	@GetMapping("/delete/{pizzaId}")
	public String deleteById(@PathVariable int pizzaId) {
		
		pizzaDAO.deleteById(pizzaId);
		
		return "redirect:/pizzas/all";
	}
}
