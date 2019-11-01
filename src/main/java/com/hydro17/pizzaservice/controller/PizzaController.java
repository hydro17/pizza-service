package com.hydro17.pizzaservice.controller;

import java.util.ArrayList;
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

import com.hydro17.pizzaservice.dao.IngredientDAO;
import com.hydro17.pizzaservice.dao.PizzaDAO;
import com.hydro17.pizzaservice.dto.PizzaDTO;
import com.hydro17.pizzaservice.entity.Ingredient;
import com.hydro17.pizzaservice.entity.Pizza;
import com.hydro17.pizzaservice.globals.PizzaServiceConstants;
import com.hydro17.pizzaservice.model.ConstraintViolation;
import com.hydro17.pizzaservice.util.PizzaUtils;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired
	private PizzaDAO pizzaDAO;
	
	@Autowired
	private IngredientDAO ingredientDAO;
	
	@Autowired
	PizzaUtils pizzaUtils;
	
	private ConstraintViolation pizzaConstraintViolation;

	@GetMapping("/list")
	public String showPizzas(Model model) {
		
		List<Pizza> pizzas = pizzaDAO.findAllOrderByIdAsc();
		
		List<PizzaDTO> pizzaDTOs = new ArrayList<>();
		
		pizzas.forEach(pizza -> {
			String allIngredientNamesAsOneString = getAllIngredientNamesAsOneString(pizza);
			
			double smallPizzaPrice = pizzaUtils.calculateSmallPizzaPrice(pizza);
			double roundedSmallPizzaPrice = roundDoubleToTwoDecimalPlaces(smallPizzaPrice);
			
			double mediumPizzaPrice = smallPizzaPrice * PizzaServiceConstants.mediumPizzaMultiplier;
			double roundedMediumPizzaPrice = roundDoubleToTwoDecimalPlaces(mediumPizzaPrice);
			
			double bigPizzaPrice = smallPizzaPrice * PizzaServiceConstants.bigPizzaMultiplier;
			double roundedBigPizzaPrice = roundDoubleToTwoDecimalPlaces(bigPizzaPrice);
			
			pizzaDTOs.add(
					new PizzaDTO(
							pizza.getId(), 
							pizza.getPizzaName(), 
							allIngredientNamesAsOneString,
							roundedSmallPizzaPrice, 
							roundedMediumPizzaPrice, 
							roundedBigPizzaPrice
							)
					);
		});
		
		model.addAttribute("pizzaConstraintViolation", this.pizzaConstraintViolation);
		this.pizzaConstraintViolation = null;
		
		model.addAttribute("pizzaDTOs", pizzaDTOs);
		
		return "pizzas/list-pizzas";
	}

	private String getAllIngredientNamesAsOneString(Pizza pizza) {
		List<String> ingredientsNames = new ArrayList<>();
		
		pizza.getIngredients().forEach(ingr -> {
			ingredientsNames.add(ingr.getIngredientName()); 
		});
		
		String allIngredientsAsString = String.join(", ", ingredientsNames);
		
		return allIngredientsAsString;
	}

	private double roundDoubleToTwoDecimalPlaces(double numberToRound) {
		double roundedNumber = (double) Math.round(numberToRound * 100d) / 100d;
		return roundedNumber;
	}
	
	@GetMapping("/add")
	public String showAddPizzaForm(Model model) {
		
		List<Ingredient> ingredients = getAllIngredientsSorted();
		
		model.addAttribute("allIngredients", ingredients);
		model.addAttribute("pizza", new Pizza());
		
		return "pizzas/add-or-update-pizza-form";
	}
	
	@PostMapping("/add")
	public String savePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("allIngredients", getAllIngredientsSorted());
			return "pizzas/add-or-update-pizza-form";
		}
		
		pizza.setId(0);
		pizzaDAO.save(pizza);
		
		return "redirect:/pizzas/list";
	}
	
	@GetMapping("/update/{pizzaId}")
	public String showUpdatePizzaForm(@PathVariable int pizzaId, Model model) {
		
		List<Ingredient> ingredients = getAllIngredientsSorted();
		
		model.addAttribute("allIngredients", ingredients);
		model.addAttribute("pizza", pizzaDAO.findById(pizzaId));
		
		return "pizzas/add-or-update-pizza-form";
	}

	@PostMapping("/update")
	public String updatePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {		
			model.addAttribute("allIngredients", getAllIngredientsSorted());
			return "pizzas/add-or-update-pizza-form";
		}
		
		pizzaDAO.save(pizza);
		
		return "redirect:/pizzas/list";
	}
	
	private List<Ingredient> getAllIngredientsSorted() {
		List<Ingredient> ingredients = ingredientDAO.findAll();
		Collections.sort(ingredients);
		return ingredients;
	}
	
	@GetMapping("/delete/{pizzaId}")
	public String deleteById(@PathVariable int pizzaId) {
		
		try {
			// Pizza with the given pizzaId could have been removed in the meantime
			if (pizzaDAO.findById(pizzaId) != null) {
				pizzaDAO.deleteById(pizzaId);
			}
		} catch (DataIntegrityViolationException e) {
			
			this.pizzaConstraintViolation = new ConstraintViolation(pizzaId, "Ta pizza nie może być teraz usunięta, znajduje się na zamówieniu.");
			
		} catch (Exception e) {
			
			System.out.println(">>> [source: CONTROLLER] Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/pizzas/list";
	}
}
