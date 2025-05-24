package com.iesports.dto;

import java.util.List;

import com.iesports.model.Match;
import com.iesports.model.Team;
import com.iesports.model.Tournament;

public class TournamentAddDTO {

	private Tournament tournament;
	private List<Team> teams;
	private List<Match> matches;
	
	public Tournament getTournament() {
		return tournament;
	}
	
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	
}
