package com.hydro17.pizzaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hydro17.pizzaservice.dto.UserPrincipal;
import com.hydro17.pizzaservice.entity.User;
import com.hydro17.pizzaservice.repository.UserRepository;

@Service
public class UserPrinciplaDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(user.getEmail());
		}
		
		return new UserPrincipal(user);
	}

}
