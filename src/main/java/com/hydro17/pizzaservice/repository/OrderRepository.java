package com.hydro17.pizzaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hydro17.pizzaservice.entity.PizzaOrder;

public interface OrderRepository extends JpaRepository<PizzaOrder, Integer> {
	
	@Query("select o from Order o where o.customer.id = ?1")
	List<Order> findByCustomerId(int customerId);
}
	