package com.hydro17.pizzaservice.dao;

import com.hydro17.pizzaservice.entity.Pizza;

public interface PizzaDAO {
	void save(Pizza pizza);
	void delete(int pizzaId);
}
