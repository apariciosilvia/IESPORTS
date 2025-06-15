package com.iesports.dto;

import jakarta.validation.constraints.NotNull;

public class TeamDeleteDTO {

    @NotNull(message = "El equipo es obligatorio")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public TeamDeleteDTO(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TeamAddDTO [id=" + id +  "]";
	}
}

