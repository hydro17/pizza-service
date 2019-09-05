package com.hydro17.pizzaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydro17.pizzaservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);
	List<User> findAllByOrderByIdAsc();
}
