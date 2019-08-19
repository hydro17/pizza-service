package com.hydro17.pizzaservice.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
//import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.hydro17.pizzaservice.service.UserPrinciplaDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserPrinciplaDetailsService userDetailsService;
	
//	@PostConstruct
//    public void completeSetup() {
//        userDetailsService = applicationContext.getBean(UserPrinciplaDetailsService.class);
//    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder())
			.and()
			.authenticationProvider(authenticationProvider())
			.jdbcAuthentication()
			.dataSource(dataSource);
			
//		UserBuilder user = User.withDefaultPasswordEncoder();

//		auth.inMemoryAuthentication()
//			.withUser("user").password("{noop}user123").roles("USER")
//			.and()
//			.withUser("cook").password("{noop}cook123").roles("COOK")
//			.and()
//			.withUser("admin").password("{noop}admin123").roles("ADMIN", "USER", "COOK");
//			.withUser(user.username("user").password("user123").roles("USER"))
//			.withUser(user.username("cook").password("cook123").roles("COOK"))
//			.withUser(user.username("admin").password("admin123").roles("ADMIN", "USER", "COOK"));
//			.withUser("user").password("user123").roles("USER")
//			.and()
//			.withUser("admin").password("admin123").roles("ADMIN", "USER", "COOK");
	}
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/").authenticated()
				.antMatchers("/pizzas/**", "/ingredients/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/orders/**").hasAnyRole("USER", "COOK")
				.antMatchers("/customers/**").hasAnyRole("ADMIN")
//				.anyRequest().permitAll()
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login-process")
				.permitAll();
	}
	
	@Bean
	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

//	@Bean
//    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//        return new SecurityEvaluationContextExtension();
//    }
}
