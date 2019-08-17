package com.hydro17.pizzaservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserBuilder user = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication()
			.withUser(user.username("user").password("user123").roles("USER"))
			.withUser(user.username("cook").password("cook123").roles("COOK"))
			.withUser(user.username("admin").password("admin123").roles("ADMIN", "USER", "COOK"));
//			.withUser("user").password("user123").roles("USER")
//			.and()
//			.withUser("admin").password("admin123").roles("ADMIN", "USER", "COOK");
//			.withUser("user").password("user123").roles("USER");
//			.and()
//			.withUser("admin").password("admin123").roles("ADMIN", "USER");
	}
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
//				.anyRequest().permitAll()
				.antMatchers("/").authenticated()
				.antMatchers("/pizzas/**", "/ingredients/**").hasRole("USER")
				.antMatchers("/orders/**").hasAnyRole("USER", "COOK")
				.antMatchers("/customers/**").hasAnyRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login-process")
				.permitAll();
	}

}
