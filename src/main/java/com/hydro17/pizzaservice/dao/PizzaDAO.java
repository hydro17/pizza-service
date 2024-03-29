package com.hydro17.pizzaservice.dao;

import java.util.List;

import com.hydro17.pizzaservice.entity.Pizza;

public interface PizzaDAO {
	
	List<Pizza> findAll();
	List<Pizza> findAllOrderByIdAsc();
	Pizza findById(int pizzaId);
	void save(Pizza pizza);
	void deleteById(int pizzaId);
}
