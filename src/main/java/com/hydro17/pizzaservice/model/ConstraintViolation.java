package com.hydro17.pizzaservice.model;

public class ConstraintViolation {
	
	public int id;
	public String comment;

	public ConstraintViolation() {
	}

	public ConstraintViolation(int id, String comment) {
		this.id = id;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}