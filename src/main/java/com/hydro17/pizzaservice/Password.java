package com.hydro17.pizzaservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {

	public static void main(String[] args) {
		BCryptPasswordEncoder password = new BCryptPasswordEncoder();
		PasswordEncoder noOpPassword = NoOpPasswordEncoder.getInstance();
		String adminPassword = "admin123";
		
		System.out.println(password.encode(adminPassword));
		System.out.println(password.encode(adminPassword));
		System.out.println(noOpPassword.encode(adminPassword));
	}

}
