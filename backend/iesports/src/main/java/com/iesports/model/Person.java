package com.iesports.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "persons")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	@NotBlank(message = "El nombre es obligatorio")
	private String name;
	
	@Email(message = "El email no es válido")
	@NotBlank(message = "El email es obligatorio")
	@Column(name = "email", unique = true)
	private String email;
	
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password;
	private int active;
	@Column(name = "forgot_password")
	private int forgotPassword;

	public Person() {

	}



	public Person(Long id, Course course, Role role, String name, String email, String password, int active, int forgotPassword) {
		super();
		this.id = id;
		this.course = course;
		this.role = role;
		this.name = name;
		this.email = email;
		this.password = password;
		this.active = active;
		this.forgotPassword = forgotPassword;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	public int getForgotPassword() {
		return forgotPassword;
	}

	public void setForgotPassword(int forgotPassword) {
		this.forgotPassword = forgotPassword;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", course=" + course + ", role=" + role + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", active=" + active + ", forgotPassword=" + forgotPassword + "]";
	}
	
}
