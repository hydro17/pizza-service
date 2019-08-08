package com.hydro17.pizzaservice.dto;

public class CustomerLoginData {
	
	private String email;
	private String password;
	
	public CustomerLoginData() {};
	
	public CustomerLoginData(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerLoginData [email=" + email + ", password=" + password + "]";
	}
}
