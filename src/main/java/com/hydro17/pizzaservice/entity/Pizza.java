package com.hydro17.pizzaservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="pizza")
public class Pizza implements Comparable<Pizza> {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private int id;
	
	@Size(min=2, max=20, message="Nazwa powinna składać się z od 2 do 20 znaków")
	@Column(name="pizza_name")
	private String pizzaName;
	
	@Size(min=1, message="Pizza musi zawierać z co najmniej 1 składnik")
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="pizza_ingredient",
			joinColumns=@JoinColumn(name="pizza_id"),
			inverseJoinColumns=@JoinColumn(name="ingredient_id"))
	private List<Ingredient> ingredients = new ArrayList<>();
	
	public Pizza() {}
	
	public Pizza(String pizzaName, List<Ingredient> ingredients) {
		this.pizzaName = pizzaName;
		this.ingredients = ingredients;
	}

	@Override
	public int compareTo(Pizza other) {
		return this.id - other.getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPizzaName() {
		return pizzaName;
	}

	public void setPizzaName(String name) {
		this.pizzaName = name;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + pizzaName + ", ingredients=" + ingredients + "]";
	}
}
