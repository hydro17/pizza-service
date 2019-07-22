package com.hydro17.pizzaservice.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hydro17.pizzaservice.entity.Pizza;

@Repository
public class PizzaDAOImpl implements PizzaDAO {

	@Autowired
	EntityManager em;
	
	@Override
	@Transactional
	public void save(Pizza pizza) {
		em.merge(pizza);
	}

	@Override
	public void delete(int pizzaId) {
		// TODO Auto-generated method stub

	}

}
