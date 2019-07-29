package com.hydro17.pizzaservice.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="pizza")
public class Pizza {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany
	@JoinTable(name="pizza_ingredient",
			joinColumns=@JoinColumn(name="ingredient_id"),
			inverseJoinColumns=@JoinColumn(name="pizza_id"))
	private Set<Ingredient> ingredients;
	
	public Pizza() {}
	
	public Pizza(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + "]";
	}
}
