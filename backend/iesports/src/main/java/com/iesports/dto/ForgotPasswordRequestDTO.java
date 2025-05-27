package com.iesports.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ForgotPasswordRequestDTO {

	@NotNull
	@NotBlank
	@Email
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public ForgotPasswordRequestDTO() {

	}

	public ForgotPasswordRequestDTO(String email) {
		this.email = email;
	}
	
}
