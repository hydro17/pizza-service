package com.hydro17.pizzaservice.dao;

import java.util.List;

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
	public List<Pizza> findAll() {
		List<Pizza> pizzas = em.createQuery("from Pizza", Pizza.class).getResultList();
		return pizzas;
	}
	
	@Override
	public Pizza findById(int pizzaId) {
		Pizza pizza = em.find(Pizza.class, pizzaId);
		return pizza;
	}
	
	@Override
	@Transactional
	public void save(Pizza pizza) {
		em.merge(pizza);
	}

	@Override
	@Transactional
	public void deleteById(int pizzaId) {
		Pizza pizza = em.find(Pizza.class, pizzaId);
		em.remove(pizza);
	}
}
