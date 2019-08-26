package com.hydro17.pizzaservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/access-denied")
	public String showAccessDeniedPage() {
    
		return "access-denied";
	}
}
