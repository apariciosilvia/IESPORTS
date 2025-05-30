package com.iesports.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ForgotPasswordRequestDTO {

	@NotNull(message = "El email es obligatorio")
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email no tiene el formato correcto")
	private String emailForgotPassword;

	public String getEmailForgotPassword() {
		return emailForgotPassword;
	}

	public void setEmailForgotPassword(String emailForgotPassword) {
		this.emailForgotPassword = emailForgotPassword;
	}

	public ForgotPasswordRequestDTO(@NotNull @NotBlank @Email String emailForgotPassword) {
		super();
		this.emailForgotPassword = emailForgotPassword;
	}

	
}
