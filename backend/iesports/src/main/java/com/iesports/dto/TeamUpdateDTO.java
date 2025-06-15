package com.iesports.dto;

import java.util.List;

import com.iesports.model.Person;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TeamUpdateDTO {

	@NotNull(message = "El equipo es obligatorio")
	private Long idTeam;

	@NotNull(message = "El nombre del equipo es obligatorio")
	private String nameTeam;

	@NotNull(message = "Debes asignar mínimo un usuario")
	@Size(min = 1, max = 15, message = "El equipo debe tener entre 1 y 15 jugadores")
	private List<Person> players;

	public TeamUpdateDTO() {
		super();
	}

	public Long getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Long idTeam) {
		this.idTeam = idTeam;
	}

	public TeamUpdateDTO(@NotNull(message = "El idTeam es obligatorio") Long idTeam,
			@NotNull(message = "El nombre es obligatorio") String nameTeam,
			@NotNull(message = "Debes asignar mínimo un usuario") @Size(min = 1, max = 15, message = "El equipo debe tener entre 1 y 15 jugadores") List<Person> players) {
		super();
		this.idTeam = idTeam;
		this.nameTeam = nameTeam;
		this.players = players;
	}

	public String getNameTeam() {
		return nameTeam;
	}

	public void setNameTeam(String nameTeam) {
		this.nameTeam = nameTeam;
	}

	public List<Person> getPlayers() {
		return players;
	}

	public void setPlayers(List<Person> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "TeamUpdateDTO [idTeam=" + idTeam + ", nameTeam=" + nameTeam + ", players=" + players + "]";
	}

}
