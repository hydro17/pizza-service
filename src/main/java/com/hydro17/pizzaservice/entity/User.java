package com.hydro17.pizzaservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="app_user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Size(min=1, max=20, message="Nazwa powinna składać się z od 1 do 20 znaków")
	@Column(name="name")
	private String name;
	
	@Email(message="Podany adres e-mail jest niepoprawny")
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@Size(min=7, message="Hasło powinno składać sie z co najmniej 7 znaków")
	@Column(name="password", nullable=false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name="role_id", nullable=false)
	private Role role;

	public User() {}
	
	public User(String name, String email, String password, Role role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
}
