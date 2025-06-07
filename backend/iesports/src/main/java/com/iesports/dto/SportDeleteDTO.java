package com.iesports.dto;

import jakarta.validation.constraints.NotNull;

public class SportDeleteDTO {

	@NotNull(message = "El id es obligatorio")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SportRegisterDTO [id=" + id + "]";
	}
	
}
