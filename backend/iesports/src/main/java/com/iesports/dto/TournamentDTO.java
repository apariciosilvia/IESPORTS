package com.iesports.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TournamentDTO {
	
	@NotBlank(message = "El nombre es obligatorio")
	@NotNull (message = "El nombre es obligatorio")
	private String name;
	@NotBlank(message = "La fecha es obligatoria")
	@NotNull
	private String date;
	@NotNull(message = "El id deporte es obligatorio")
	@Min(value = 1, message = "Debe seleccionar un deporte válido")
	private Long sportId;
	@Min(value = 4, message = "Tienen que ser 4 equipos totales como mínimo")
	@NotNull (message = "Tienen que ser 4 equipos totales como mínimo")
	private int maxTeams;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Long getSportId() {
		return sportId;
	}
	public void setSportId(Long sportId) {
		this.sportId = sportId;
	}
	
	public int getMaxTeams() {
		return maxTeams;
	}
	public void setMaxEquipos(int max_equipos) {
		this.maxTeams = max_equipos;
	}
	@Override
	public String toString() {
		return "TournamentDTO [name=" + name + ", date=" + date + ", sportId=" + sportId + ", maxEquipos=" + maxTeams
				+ "]";
	}
	
	
}
