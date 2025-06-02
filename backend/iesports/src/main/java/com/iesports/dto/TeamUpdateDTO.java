package com.iesports.dto;

import java.util.List;

import com.iesports.model.Person;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TeamUpdateDTO {

    @NotNull(message = "El nombre no debe estar vacío")
    private String name;

    @NotNull(message = "Debes asignar mínimo un usuario")
    @Size(min = 1, max = 15, message = "El equipo debe tener entre 1 y 15 jugadores")
    private List<Person> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPlayers() {
        return players;
    }

    public void setPlayers(List<Person> players) {
        this.players = players;
    }
    

	public TeamUpdateDTO(String name, List<Person> players) {
		this.name = name;
		this.players = players;
	}

	@Override
	public String toString() {
		return "TeamAddDTO [name=" + name + ", players=" + players + "]";
	}
}

