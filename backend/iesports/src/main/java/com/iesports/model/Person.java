package com.iesports.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa una persona (usuario del sistema)")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID único de la persona", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	   @Schema(description = "Curso asociado a la persona")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@Schema(description = "Rol asignado a la persona")
	private Role role;

	@NotBlank(message = "El nombre es obligatorio")
	@Schema(description = "Nombre completo de la persona", example = "Juan Pérez")
	private String name;

	@Email(message = "El email no es válido")
	@NotBlank(message = "El email es obligatorio")
	@Column(name = "email", unique = true)
	@Schema(description = "Correo electrónico único de la persona", example = "juan.perez@example.com")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	@Schema(description = "Contraseña encriptada de la persona")
	private String password;
	@Schema(description = "Estado activo de la persona (1 = activo, 0 = inactivo)", example = "1")
	private int active;
	@Column(name = "temp_password")
    @Schema(description = "Indica si la contraseña es temporal (1 = sí, 0 = no)", example = "0")
	private int tempPassword;

	public Person() {

	}

	public Person(Long id, Course course, Role role, @NotBlank(message = "El nombre es obligatorio") String name,
			@Email(message = "El email no es válido") @NotBlank(message = "El email es obligatorio") String email,
			@NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password,
			int active, int tempPassword) {
		super();
		this.id = id;
		this.course = course;
		this.role = role;
		this.name = name;
		this.email = email;
		this.password = password;
		this.active = active;
		this.tempPassword = tempPassword;
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

	public int getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(int tempPassword) {
		this.tempPassword = tempPassword;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", course=" + course + ", role=" + role + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", active=" + active + ", tempPassword=" + tempPassword + "]";
	}

}
