package com.hydro17.pizzaservice.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.entity.Role;

@Component
public class RoleToStringIdConverter implements Converter<Role, String> {

	@Override
	public String convert(Role role) {
		
		return String.valueOf(role.getId());
	}
}
