package com.hydro17.pizzaservice.controller;

import java.util.ArrayList;
import java.util.Collection;
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
import com.hydro17.pizzaservice.entity.Ingredient;
import com.hydro17.pizzaservice.entity.Pizza;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	private class PizzaDTO {
		private int id;
		private String name;
		private String ingredients;
		private double price;
		
		public PizzaDTO(int id, String name, String ingredients, double price) {
			this.id = id;
			this.name = name;
			this.ingredients = ingredients;
			this.price = price;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getIngredients() {
			return ingredients;
		}

		public double getPrice() {
			return price;
		}
	}
	
	@Autowired
	private PizzaDAO pizzaDAO;
	
	@Autowired
	private IngredientDAO ingredientDAO;

	@GetMapping("/all")
	public String showPizzas(Model model) {
		
		List<Pizza> pizzas = pizzaDAO.findAll();
		List<PizzaDTO> pizzasDTO = new ArrayList<>();
		
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

			pizzasDTO.add(new PizzaDTO(pizza.getId(), pizza.getName(), allIngredientsAsString, pizzaPrice));
		});
		
		model.addAttribute("pizzasDTO", pizzasDTO);
		
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
