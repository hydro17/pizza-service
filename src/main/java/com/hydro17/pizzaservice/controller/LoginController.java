package com.hydro17.pizzaservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String showLoginForm() {
		
		return "/customers/login-form";
	}

}
