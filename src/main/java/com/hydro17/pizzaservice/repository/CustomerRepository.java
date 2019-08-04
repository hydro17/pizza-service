package com.hydro17.pizzaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydro17.pizzaservice.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
