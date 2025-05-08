package com.iesports.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonRegisterDTO {

	@NotBlank(message = "El nombre es obligatorio")
	private String name;

	@Email(message = "El email no es válido")
	@NotBlank(message = "El email es obligatorio")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password1;

	@NotBlank(message = "La confirmación de contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password2;

	@NotNull(message = "El curso es obligatorio")
	@Min(value = 1, message = "Debe seleccionar un curso válido")
	private Long courseId;

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

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "PersonRegisterDTO [name=" + name + ", email=" + email + ", password1=" + password1 + ", password2="
				+ password2 + ", courseId=" + courseId + "]";
	}
}
