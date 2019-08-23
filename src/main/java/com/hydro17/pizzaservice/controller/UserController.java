package com.hydro17.pizzaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hydro17.pizzaservice.dto.UserPrincipal;
import com.hydro17.pizzaservice.entity.User;
import com.hydro17.pizzaservice.repository.RoleRepository;
import com.hydro17.pizzaservice.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/users/list")
	public String listUsers(Model model) {
		
		model.addAttribute("users", userRepository.findAll());
		
		return "/users/list-users";
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
		
		return "/users/login-form";
	}
	
	@GetMapping("/register-user")
	public String showRegisterUserForm(Model model) {
		
		User user = new User();
		user.setRole(roleRepository.findByName("ROLE_CUSTOMER"));
//		user.setRole(roleRepository.findByName("ROLE_ADMIN"));
		
		model.addAttribute("allRoles", roleRepository.findAll());
		model.addAttribute("user", user);
		
		return "users/register-or-update-user-form";
	}
	
	@PostMapping("/register-user")
	public String processRegisterUserForm(@ModelAttribute User user) {
		
		user.setId(0);
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		
		userRepository.save(user);
		
		return "redirect:/users/list";
	}
	
	@GetMapping("/users/update/{userId}")
	public String showUpdateUserForm(@PathVariable int userId, Model model) {
		
		model.addAttribute("allRoles", roleRepository.findAll());
		model.addAttribute("user", userRepository.findById(userId).get());
		
		return "/users/register-or-update-user-form";
	}
	
	@PostMapping("/users/update")
	public String updateUser(@ModelAttribute User user) {
		
		userRepository.save(user);
		
		//Update name of User instance wrapped by UserPrincipal to be correctly display in the top bar,
		//without the need to log out and log in after changing user name
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		((UserPrincipal)principal).getUser().setName(user.getName());;
		
		return "redirect:/users/list";
	}
	
	@GetMapping("/users/delete/{userId}")
	public String deleteUser(@PathVariable int userId) {
		
		userRepository.deleteById(userId);
		
		return "redirect:/users/list";
	}
}
