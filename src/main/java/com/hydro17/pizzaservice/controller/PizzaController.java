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

import com.hydro17.pizzaservice.dao.PizzaDAO;
import com.hydro17.pizzaservice.entity.Pizza;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired
	private PizzaDAO pizzaDAO;

	@GetMapping("/add")
	public String showAddPizzaForm(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "add-pizza-form";
	}
	
	@PostMapping("/add")
	public String processAddPizzaForm(@ModelAttribute Pizza pizza) {
		pizzaDAO.save(pizza);
		return "redirect:/pizzas/all";
	}
	
	@GetMapping("/all")
	public String showPizzas(Model model) {
		model.addAttribute("pizzas", pizzaDAO.findAll());
		return "list-all-pizzas";
	}
	
//	@DeleteMapping("/{pizzaId}")
//	public void deleteById(@PathVariable int pizzaId) {
//		
//	}
//	
	@GetMapping("/delete/{pizzaId}")
	public String deleteById(@PathVariable int pizzaId) {
		pizzaDAO.deleteById(pizzaId);
		return "redirect:/pizzas/all";
	}
}
