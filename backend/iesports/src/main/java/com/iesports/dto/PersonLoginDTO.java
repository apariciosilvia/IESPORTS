package com.iesports.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PersonLoginDTO {
	
	@Email(message = "El email no tiene el formato correcto")
	@NotBlank(message = "El email es obligatorio")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword1(String password1) {
		this.password = password1;
	}
}
