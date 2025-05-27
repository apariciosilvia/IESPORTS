package com.iesports.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangeForgottenPasswordDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password1;

	@NotBlank(message = "La confirmación de contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password2;
	
	public ChangeForgottenPasswordDTO() {
		
	}

	public ChangeForgottenPasswordDTO(Long id, String password1, String password2) {
		this.id = id;
		this.password1 = password1;
		this.password2 = password2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ChangeForgottenPasswordDTO [id=" + id + ", password1=" + password1 + ", password2=" + password2 + "]";
	}
	
}
