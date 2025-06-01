package com.iesports.dto;

import java.util.List;

import com.iesports.model.Match;
import com.iesports.model.Team;
import com.iesports.model.Tournament;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TournamentAddDTO {
	
	@NotNull(message = "El nombre del torneo es obligatorio")
	private String name;
	@NotNull(message = "El deporte tiene que ser obligatorio")
	private Long sportId;
	private int numTeams;
	
	@NotNull(message = "Debes asignar todos los partidos")
	private List<MatchDTO> matches;
	
	public TournamentAddDTO() {
		super();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getSportId() {
		return sportId;
	}

	public void setSportId(Long sportId) {
		this.sportId = sportId;
	}

	public int getNumTeams() {
		return numTeams;
	}
	public void setNumTeams(int numTeams) {
		this.numTeams = numTeams;
	}
	public List<MatchDTO> getMatches() {
		return matches;
	}
	public void setMatches(List<MatchDTO> matches) {
		this.matches = matches;
	}
	
	
	
	
	
	
}
