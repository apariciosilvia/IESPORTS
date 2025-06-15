package com.iesports.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PersonRegisterByAdminDTO {

	@NotBlank(message = "El nombre es obligatorio")
	private String name;
	
	@NotNull(message = "El rol ha de ser asignado")
	private Long roleId;

	@Email(message = "El email no es válido")
	@NotBlank(message = "El email es obligatorio")
	private String email;

	@NotNull(message = "El curso es obligatorio")
	@Min(value = 1, message = "Debe seleccionar un curso válido")
	private Long courseId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "PersonRegisterByAdminDTO [name=" + name + ", roleId=" + roleId + ", email=" + email + ", courseId="
				+ courseId + "]";
	}
}
