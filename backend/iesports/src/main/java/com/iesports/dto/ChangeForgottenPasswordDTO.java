package com.iesports.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChangeForgottenPasswordDTO {

	@NotNull(message = "La persona no puede ser nula")
	private Long personId;
	
	@NotNull(message = "La contraseña es obligatoria")
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password1;

	@NotNull(message = "La confirmación de contraseña no puede estar vacia")
	@NotBlank(message = "La confirmación de contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password2;
	
	public ChangeForgottenPasswordDTO() {
		
	}

	public ChangeForgottenPasswordDTO(@NotNull(message = "La persona no puede ser nula") Long personId,
			@NotNull(message = "La contraseña es obligatoria") @NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password1,
			@NotNull(message = "La confirmación de contraseña no puede estar vacia") @NotBlank(message = "La confirmación de contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password2) {
		super();
		this.personId = personId;
		this.password1 = password1;
		this.password2 = password2;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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
		return "ChangeForgottenPasswordDTO [personId=" + personId + ", password1=" + password1 + ", password2="
				+ password2 + "]";
	}
}
