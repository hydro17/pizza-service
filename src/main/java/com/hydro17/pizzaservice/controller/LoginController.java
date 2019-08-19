package com.hydro17.pizzaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hydro17.pizzaservice.entity.User;
import com.hydro17.pizzaservice.repository.RoleRepository;
import com.hydro17.pizzaservice.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String showLoginForm() {
		
		return "/customers/login-form";
	}
	
	@GetMapping("/register-user")
	public String showRegisterUserForm(Model model) {
		
		User user = new User();
		user.setId(0);
		user.setRole(roleRepository.findByName("ROLE_ADMIN"));
		
		model.addAttribute("user", user);
		
		return "users/register-or-update-user-form";
	}
	
	@PostMapping("/register-user")
	public String processRegisterUserForm(@ModelAttribute User user) {
		
		userRepository.save(user);
		
		return "redirect:/pizzas/list";
	}
}
