package com.iesports.dto;

import java.util.List;

import com.iesports.model.Sport;
import com.iesports.model.Team;

public class TeamInfoDTO {

	private Team team;
	
	private List<Sport> sports;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Sport> getSports() {
		return sports;
	}

	public void setSports(List<Sport> sports) {
		this.sports = sports;
	}
	
}
