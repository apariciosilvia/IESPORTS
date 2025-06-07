package com.iesports.dto;

import java.util.List;

import com.iesports.model.Person;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TeamDeleteDTO {

    @NotNull(message = "El nombre no debe estar vacío")
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

