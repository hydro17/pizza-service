package com.hydro17.pizzaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydro17.pizzaservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
