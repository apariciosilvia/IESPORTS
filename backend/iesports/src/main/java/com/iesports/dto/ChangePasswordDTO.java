package com.iesports.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChangePasswordDTO {

	@NotNull(message = "El usuario es obligatorio")
	private Long personId;
	
	@NotNull(message = "La contraseña actual es obligatoria")
	@NotBlank(message = "La contraseña actual es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String currentPassword;
	
	@NotNull(message = "La contraseña no puede estar vacia")
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password1ChangePassword;

	@NotNull(message = "La confirmación de contraseña no puede estar vacia")
	@NotBlank(message = "La confirmación de contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password2ChangePassword;
	
	public ChangePasswordDTO() {
		
	}

	public ChangePasswordDTO(@NotNull(message = "La persona no puede ser nula") Long personId,
			@NotNull(message = "La contraseña actual es obligatoria") @NotBlank(message = "La contraseña actual es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String currentPassword,
			@NotNull(message = "La contraseña no puede estar vacia") @NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password1ChangePassword,
			@NotNull(message = "La confirmación de contraseña no puede estar vacia") @NotBlank(message = "La confirmación de contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password2ChangePassword) {
		super();
		this.personId = personId;
		this.currentPassword = currentPassword;
		this.password1ChangePassword = password1ChangePassword;
		this.password2ChangePassword = password2ChangePassword;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getPassword1ChangePassword() {
		return password1ChangePassword;
	}

	public void setPassword1ChangePassword(String password1ChangePassword) {
		this.password1ChangePassword = password1ChangePassword;
	}

	public String getPassword2ChangePassword() {
		return password2ChangePassword;
	}

	public void setPassword2ChangePassword(String password2ChangePassword) {
		this.password2ChangePassword = password2ChangePassword;
	}

	@Override
	public String toString() {
		return "ChangePasswordDTO [personId=" + personId + ", currentPassword=" + currentPassword
				+ ", password1ChangePassword=" + password1ChangePassword + ", password2ChangePassword="
				+ password2ChangePassword + "]";
	}
}
