package com.hydro17.pizzaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hydro17.pizzaservice.entity.PizzaOrder;
import com.hydro17.pizzaservice.entity.User;

public interface OrderRepository extends JpaRepository<PizzaOrder, Integer> {
	
//	@Query("select po from PizzaOrder po where po.user.id = ?1")
//	List<PizzaOrder> findByUserId(int userId);
	
	List<PizzaOrder> findAllByUser(User user);
	List<PizzaOrder> findAllByUserOrderByOrderDateDesc(User user);
	
	List<PizzaOrder> findAllByOrderByQuantityDesc();
	List<PizzaOrder> findAllByOrderByOrderDateDesc();
}
	