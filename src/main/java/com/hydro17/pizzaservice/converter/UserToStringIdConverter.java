package com.hydro17.pizzaservice.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.entity.User;

@Component
public class UserToStringIdConverter implements Converter<User, String> {

	@Override
	public String convert(User user) {
		
		return String.valueOf(user.getId());
	}
}
