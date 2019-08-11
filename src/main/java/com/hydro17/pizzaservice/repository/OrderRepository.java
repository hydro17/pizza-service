package com.hydro17.pizzaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hydro17.pizzaservice.entity.PizzaOrder;

public interface OrderRepository extends JpaRepository<PizzaOrder, Integer> {
	
	@Query("select po from PizzaOrder po where po.customer.id = ?1")
	List<PizzaOrder> findByCustomerId(int customerId);
}
	