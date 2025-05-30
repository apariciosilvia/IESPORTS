package com.iesports.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChangeNameAndEmailDTO {
	
	@NotNull(message = "La persona no puede ser nula")
	private Long personId;
	
	@NotBlank(message = "El nombre es obligatorio")
	private String name;
	
	@Email(message = "El email no es válido")
	@NotBlank(message = "El email es obligatorio")
	private String email;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	@Override
	public String toString() {
		return "ChangeNameAndEmailDTO [personId=" + personId + ", name=" + name + ", email=" + email + "]";
	}

	
}
