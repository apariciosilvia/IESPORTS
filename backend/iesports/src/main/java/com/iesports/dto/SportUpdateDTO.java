package com.iesports.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SportUpdateDTO {
	
	@NotNull(message = "El deporte es obligatorio")
    private Long id;
    
	@NotBlank(message = "El nombre del deporte es obligatorio")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SportUpdateDTO [id=" + id + ", name=" + name + "]";
	}

}
