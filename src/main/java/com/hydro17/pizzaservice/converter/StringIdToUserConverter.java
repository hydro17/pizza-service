package com.hydro17.pizzaservice.converter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.entity.Customer;
import com.hydro17.pizzaservice.repository.CustomerRepository;

@Component
public class StringIdToCustomerConverter implements Converter<String, Customer> {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer convert(String customerId) {
		
		int customerIdAsInt = Integer.parseInt(customerId);
		
		return getCustomerBy(customer -> customer.getId() == customerIdAsInt);
	}
	
	private Customer getCustomerBy(Predicate<Customer> isHeRequiredCustomer) {
		
		List<Customer> customers = customerRepository.findAll();
		
		for (Customer customer : customers) {
			if (isHeRequiredCustomer.test(customer)) return customer;
		}
		
		return null;
	}

}
