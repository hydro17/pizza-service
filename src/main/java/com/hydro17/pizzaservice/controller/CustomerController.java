package com.hydro17.pizzaservice.controller;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hydro17.pizzaservice.dto.CustomerLoginData;
import com.hydro17.pizzaservice.entity.Customer;
import com.hydro17.pizzaservice.repository.CustomerRepository;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/all")
	public String listAll(Model model) {
		
		model.addAttribute("customers", customerRepository.findAll());
		
		return "list-all-customers";
	}

	@GetMapping("/add")
	public String showAddCustomerForm(Model model) {
		
		model.addAttribute("customer", new Customer());
		
		return "register-or-update-customer-form";
	}
	
	@PostMapping("/add")
	public String saveCustomer(@ModelAttribute Customer customer) {
		
		customer.setId(0);
		customerRepository.save(customer);
		
		return "redirect:/customers/all";
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		
		model.addAttribute("customer", new CustomerLoginData());
		
		return "login-form";
	}
	
	@PostMapping("/login")
	public String verifyCustomer(@ModelAttribute CustomerLoginData customerLogingData, Model model) {
		
		List<Customer> customers = customerRepository.findAll();
		
		Customer authenticatedCustomer = getCustomerIfAuthenticated(customers, customerLogingData); 
		
		if (authenticatedCustomer == null) {
			model.addAttribute("customer", customerLogingData);
			model.addAttribute("error", "Nieprawidłoy e-mail lub hasło");
			return "login-form";
		}
		
		return "redirect:/orders/all/" + authenticatedCustomer.getId();
	}
	
	private Customer getCustomerIfAuthenticated(Collection<Customer> customers, CustomerLoginData customerLogingData) {
		
		for (Customer customer : customers) {
			if (customer.getEmail() == customerLogingData.getEmail() 
				&& customer.getPassword() == customerLogingData.getPassword()) {
				return customer;
			}
		}
		
		return null;
	}
	
	@GetMapping("/update/{customerId}")
	public String showUpdateCustomerForm(@PathVariable int customerId, Model model) {
		
		model.addAttribute("customer", customerRepository.findById(customerId).get());
		
		return "register-or-update-customer-form";
	}
	
	@PostMapping("/update")
	public String updateCustomer(@ModelAttribute Customer customer) {
		
		customerRepository.save(customer);
		
		return "redirect:/customers/all";
	}
	
	@GetMapping("/delete/{customerId}")
	public String deleteById(@PathVariable int customerId ) {
		
		customerRepository.deleteById(customerId);
		
		return "redirect:/customers/all";
	}
}
