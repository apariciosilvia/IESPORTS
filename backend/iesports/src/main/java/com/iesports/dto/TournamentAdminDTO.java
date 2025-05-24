package com.iesports.dto;

import java.util.List;

import com.iesports.model.Team;
import com.iesports.model.Tournament;

public class TournamentAdminDTO {

	private List<Team> teams;
	private Tournament tournament;
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	public Tournament getTournament() {
		return tournament;
	}
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	} 
}
