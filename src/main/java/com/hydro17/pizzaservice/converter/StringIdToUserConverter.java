package com.hydro17.pizzaservice.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.hydro17.pizzaservice.entity.User;
import com.hydro17.pizzaservice.repository.UserRepository;

//@Component
public class StringIdToUserConverter implements Converter<String, User> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User convert(String userId) {
		
		int userIdAsInt = Integer.parseInt(userId);
		
		return userRepository.findById(userIdAsInt).get();
	}
}
