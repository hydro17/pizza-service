package com.hydro17.pizzaservice.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydro17.pizzaservice.dao.PizzaDAO;
import com.hydro17.pizzaservice.entity.Customer;
import com.hydro17.pizzaservice.entity.Order;
import com.hydro17.pizzaservice.entity.Pizza;
import com.hydro17.pizzaservice.enums.OrderStatus;
import com.hydro17.pizzaservice.enums.PizzaSize;
import com.hydro17.pizzaservice.repository.OrderRepository;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PizzaDAO pizzaDAO;
	
	@GetMapping("/list")
	public String listAll(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
//		Customer authenticatedCustomer = (Customer) session.getAttribute("authenticatedCustomer");
//		
//		if (authenticatedCustomer == null) {
//			model.addAttribute("orders", new ArrayList<Order>());
//			return "orders/list";
//		}
//		
//		model.addAttribute("orders", orderRepository.findByCustomerId(authenticatedCustomer.getId()));
		
		model.addAttribute("orders", orderRepository.findAll());
		
		return "orders/list";
	}
	
	@GetMapping("/add")
	public String selectPizza() {
		return "redirect:/pizzas/all";
	}
	
	@GetMapping("/add/{pizzaId}")
	public String showAddOrderForm(@PathVariable int pizzaId, Model model) {
		
		Order order = new Order();
		order.setPizza(getPizzaById(pizzaId));
		
		model.addAttribute("allSizes", PizzaSize.values());
		model.addAttribute("allStatuses", OrderStatus.values());
		model.addAttribute("order",  order);
		
		return "orders/add-or-update-form";
	}
	
	private Pizza getPizzaById(int pizzaId) {
		
		for (Pizza pizza : pizzaDAO.findAll()) {
			if (pizza.getId() == pizzaId) return pizza;
		}
		
		return null;
	}

	@PostMapping("/add")
	public String saveOrder(@ModelAttribute Order order) {
		
		order.setId(0);
		order.setOrderStatus(OrderStatus.ORDERED);
		order.setCustomer(new Customer());
		orderRepository.save(order);
		
		return "redirect:/orders/list";
	}
	
	@GetMapping("/update/{orderId}")
	public String showUpdateOrderForm(@PathVariable int orderId,  Model model) {
		
		model.addAttribute("order", orderRepository.findById(orderId));	
		
		return "orders/add-or-update-form";
	}
	
	@PostMapping("/update")
	public String updateOrder(@ModelAttribute Order order) {
		
		orderRepository.save(order);
		
		return "redirect:/orders/list";
	}
	
	@GetMapping("/delete/{orderId}")
	public String deleteById(@PathVariable int orderId) {
		
		orderRepository.deleteById(orderId);
		
		return "redirect:/orders/list";
	}
}
