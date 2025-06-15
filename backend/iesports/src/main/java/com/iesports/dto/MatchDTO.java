package com.iesports.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public class MatchDTO {

	@NotBlank(message = "El equipo 1 es obligatorio")
	private Long team1Id;
	@NotBlank(message = "El equipo 2 es obligatorio")
	private Long team2Id;
	
	private Date matchDate;
	
	public Long getTeam1Id() {
		return team1Id;
	}
	public void setTeam1Id(Long team1Id) {
		this.team1Id = team1Id;
	}
	public Long getTeam2Id() {
		return team2Id;
	}
	public void setTeam2Id(Long team2Id) {
		this.team2Id = team2Id;
	}
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}
	
	
	
}
