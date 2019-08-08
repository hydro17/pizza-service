package com.hydro17.pizzaservice.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.entity.Customer;

@Component
public class CustomerToStringIdConverter implements Converter<Customer, String> {

	@Override
	public String convert(Customer customer) {
		
		return String.valueOf(customer.getId());
	}
}
