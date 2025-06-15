package com.iesports.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactFormRequestDTO {
	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email no tiene el formato correcto")
    private String email;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String message;

    // Getters y setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

