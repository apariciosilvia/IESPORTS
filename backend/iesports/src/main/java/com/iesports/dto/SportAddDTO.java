package com.iesports.dto;

import jakarta.validation.constraints.NotBlank;

public class SportAddDTO {

	@NotBlank(message = "El nombre del deporte es obligatorio")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SportRegisterDTO [name=" + name + "]";
	}

}
