package com.hydro17.pizzaservice.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydro17.pizzaservice.dao.PizzaDAO;
import com.hydro17.pizzaservice.entity.PizzaOrder;
import com.hydro17.pizzaservice.entity.User;
import com.hydro17.pizzaservice.enums.PizzaOrderStatus;
import com.hydro17.pizzaservice.enums.PizzaSize;
import com.hydro17.pizzaservice.repository.OrderRepository;
import com.hydro17.pizzaservice.repository.UserRepository;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PizzaDAO pizzaDAO;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/list")
	public String listAll(Model model, HttpServletRequest request, Principal principal) {
		
		User loggedInUser = getLoggedInUser(principal);
		
		if (request.isUserInRole("ADMIN")) {
			model.addAttribute("pizzaOrders", orderRepository.findAllByOrderByOrderDateDesc());
		} else {
			model.addAttribute("pizzaOrders", orderRepository.findAllByUserOrderByOrderDateDesc(loggedInUser));
		}
		
		return "orders/list-pizza-orders";
	}
	
	@GetMapping("/add")
	public String selectPizza() {
		return "redirect:/pizzas/list?select";
	}
	
	@GetMapping("/add/{pizzaId}")
	public String showAddPizzaOrderForm(@PathVariable int pizzaId, Model model, Principal principal) {
		
		PizzaOrder pizzaOrder = new PizzaOrder();
		pizzaOrder.setPizza(pizzaDAO.findById(pizzaId)); 
		
		//Set the initial order status
		pizzaOrder.setStatus(PizzaOrderStatus.ORDERED);
		
		//Set the suggested pizza size
		pizzaOrder.setPizzaSize(PizzaSize.LARGE);
		
		pizzaOrder.setOrderDate(LocalDateTime.now(ZoneId.of("Europe/Warsaw")));
		
		User loggedInUser = getLoggedInUser(principal);  
		pizzaOrder.setUser(loggedInUser);
		
		model.addAttribute("allSizes", PizzaSize.values());
		model.addAttribute("pizzaOrder", pizzaOrder);
		
		return "orders/add-or-update-pizza-order-form";
	}

	private User getLoggedInUser(Principal principal) {
		
		// principal.getName() means get email, because we use email as user name
		// method getName() is imposed by Principal interface
		String email = principal.getName();
		
		return userRepository.findByEmail(email);
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
			PizzaOrder order = new PizzaOrder(); 
			order.setId(9999); 
			return order;
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
	
	@GetMapping("/change-status/{orderId}")
	public String showChangeOrderStatusForm(@PathVariable int orderId, Model model) {
		
		model.addAttribute("pizzaOrder", orderRepository.findById(orderId).get());
		model.addAttribute("allStatuses", PizzaOrderStatus.values());
		
		return "orders/change-order-status-form";
	}
	
	@PostMapping("/change-status")
	public String changeOrderStatus(@ModelAttribute PizzaOrder pizzaOrder) {
		
		orderRepository.save(pizzaOrder);
		
		return "redirect:/orders/list";
	}
	
	
	
	
	
}
