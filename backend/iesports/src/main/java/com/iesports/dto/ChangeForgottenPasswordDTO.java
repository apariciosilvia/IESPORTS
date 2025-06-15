package com.iesports.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChangeForgottenPasswordDTO {

	@NotNull(message = "El usuario es obligatorio")
	private Long personId;
	
	@NotNull(message = "La contraseña es obligatoria")
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password1TempPaswword;

	@NotNull(message = "La confirmación de contraseña no puede estar vacia")
	@NotBlank(message = "La confirmación de contraseña es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password2TempPaswword;
	
	public ChangeForgottenPasswordDTO() {
		
	}

	public ChangeForgottenPasswordDTO(@NotNull(message = "La persona no puede ser nula") Long personId,
			@NotNull(message = "La contraseña es obligatoria") @NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password1TempPaswword,
			@NotNull(message = "La confirmación de contraseña no puede estar vacia") @NotBlank(message = "La confirmación de contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password2TempPaswword) {
		super();
		this.personId = personId;
		this.password1TempPaswword = password1TempPaswword;
		this.password2TempPaswword = password2TempPaswword;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPassword1TempPaswword() {
		return password1TempPaswword;
	}

	public void setPassword1TempPaswword(String password1TempPaswword) {
		this.password1TempPaswword = password1TempPaswword;
	}

	public String getPassword2TempPaswword() {
		return password2TempPaswword;
	}

	public void setPassword2TempPaswword(String password2TempPaswword) {
		this.password2TempPaswword = password2TempPaswword;
	}

	@Override
	public String toString() {
		return "ChangeForgottenPasswordDTO [personId=" + personId + ", password1TempPaswword=" + password1TempPaswword
				+ ", password2TempPaswword=" + password2TempPaswword + "]";
	}
}
