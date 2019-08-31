package com.hydro17.pizzaservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

import com.hydro17.pizzaservice.globals.PizzaServiceConstants;

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
				ingredientsAsString.add(ingr.getIngredientName()); 
			});
			
			String allIngredientsAsString = String.join(", ", ingredientsAsString);
			
			double smallPizzaPrice = 0;
			
			for (Ingredient ingr : pizza.getIngredients()) {
				smallPizzaPrice += ingr.getPrice();
			}
			
			double mediumPizzaPrice = smallPizzaPrice * PizzaServiceConstants.mediumPizzaMultiplier;
			double roundedMediumPizzaPrice = roundDoubleToTwoDecimalPlaces(mediumPizzaPrice);
			
			double bigPizzaPrice = smallPizzaPrice * PizzaServiceConstants.bigPizzaMultiplier;
			double roundedBigPizzaPrice = roundDoubleToTwoDecimalPlaces(bigPizzaPrice);
			
			pizzaDTOs.add(new PizzaDTO(pizza.getId(), pizza.getPizzaName(), allIngredientsAsString, 
				smallPizzaPrice, roundedMediumPizzaPrice, roundedBigPizzaPrice));
		});
		
		model.addAttribute("pizzaDTOs", pizzaDTOs);
		
		return "pizzas/list-pizzas";
	}

	private double roundDoubleToTwoDecimalPlaces(double numberToRound) {
		double roundedNumber = (double) Math.round(numberToRound * 100d) / 100d;
		return roundedNumber;
	}
	
	@GetMapping("/add")
	public String showAddPizzaForm(Model model) {
		
		model.addAttribute("allIngredients", ingredientDAO.findAll());
		model.addAttribute("pizza", new Pizza());
		
		return "pizzas/add-or-update-pizza-form";
	}
	
	@PostMapping("/add")
	public String savePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			
			model.addAttribute("allIngredients", ingredientDAO.findAll());
			
			return "pizzas/add-or-update-pizza-form";
		}
		
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
	public String updatePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("allIngredients", ingredientDAO.findAll());
			
			return "pizzas/add-or-update-pizza-form";
		}
		
		pizzaDAO.save(pizza);
		
		return "redirect:/pizzas/list";
	}
	
	@GetMapping("/delete/{pizzaId}")
	public String deleteById(@PathVariable int pizzaId) {
		
		if (pizzaDAO.findById(pizzaId) != null) {
			pizzaDAO.deleteById(pizzaId);
		}
		
		return "redirect:/pizzas/list";
	}
}
