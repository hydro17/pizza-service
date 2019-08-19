package com.hydro17.pizzaservice.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hydro17.pizzaservice.entity.Role;
import com.hydro17.pizzaservice.repository.RoleRepository;

@Component
public class StringIdToRoleConverter implements Converter<String, Role> {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role convert(String roleId) {
		
		return roleRepository.findById(Integer.parseInt(roleId)).get();
	}

}
