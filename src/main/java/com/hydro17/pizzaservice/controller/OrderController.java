package com.hydro17.pizzaservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydro17.pizzaservice.dao.PizzaDAO;
import com.hydro17.pizzaservice.entity.Customer;
import com.hydro17.pizzaservice.entity.Pizza;
import com.hydro17.pizzaservice.entity.PizzaOrder;
import com.hydro17.pizzaservice.enums.PizzaOrderStatus;
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
		
//		Customer authenticatedCustomer = (Customer) session.getAttribute("authCustomer");
//		
//		if (authenticatedCustomer == null) {
//			model.addAttribute("orders", new ArrayList<Order>());
//			return "orders/list";
//		}
//		
//		model.addAttribute("orders", orderRepository.findByCustomerId(authenticatedCustomer.getId()));
		
		model.addAttribute("pizzaOrders", orderRepository.findAll());
		
		return "orders/list-pizza-orders";
	}
	
	@GetMapping("/add")
	public String selectPizza() {
		return "redirect:/pizzas/list?select";
	}
	
	@GetMapping("/add/{pizzaId}")
	public String showAddPizzaOrderForm(@PathVariable int pizzaId, Model model, HttpServletRequest request) {
		
		PizzaOrder pizzaOrder = new PizzaOrder();
		pizzaOrder.setPizza(getPizzaById(pizzaId));
		pizzaOrder.setStatus(PizzaOrderStatus.ORDERED);
		pizzaOrder.setPizzaSize(PizzaSize.LARGE);
		
		Customer authCustomer = (Customer) request.getSession().getAttribute("authCustomer"); 
		
//		Object princial = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (authCustomer == null) {
			Customer customer = new Customer();
			customer.setId(4);
			pizzaOrder.setCustomer(customer);
		} else {
			pizzaOrder.setCustomer(authCustomer);
		}
		
		model.addAttribute("allSizes", PizzaSize.values());
		model.addAttribute("pizzaOrder", pizzaOrder);
		
		return "orders/add-or-update-pizza-order-form";
	}
	
	private Pizza getPizzaById(int pizzaId) {
		
		for (Pizza pizza : pizzaDAO.findAll()) {
			if (pizza.getId() == pizzaId) return pizza;
		}
		
		return null;
	}

	@PostMapping("/add")
	public String savePizzaOrder(@ModelAttribute PizzaOrder pizzaOrder) {
		
		pizzaOrder.setId(0);
		
		orderRepository.save(pizzaOrder);
		
		return "redirect:/orders/list";
	}
	
	@GetMapping("/update/{orderId}")
	public String showUpdatePizzaOrderForm(@PathVariable int orderId,  Model model) {
		
		model.addAttribute("pizzaOrder", orderRepository.findById(orderId).orElseGet(() -> {
			PizzaOrder o = new PizzaOrder(); 
			o.setId(9999); 
			return o;
		}));	
		
		model.addAttribute("allSizes", PizzaSize.values());
		
		return "orders/add-or-update-pizza-order-form";
	}
	
	@PostMapping("/update")
	public String updatePizzaOrder(@ModelAttribute PizzaOrder order) {
		
		orderRepository.save(order);
		
		return "redirect:/orders/list";
	}
	
	@GetMapping("/delete/{orderId}")
	public String deleteById(@PathVariable int orderId) {
		
		orderRepository.deleteById(orderId);
		
		return "redirect:/orders/list";
	}
}
