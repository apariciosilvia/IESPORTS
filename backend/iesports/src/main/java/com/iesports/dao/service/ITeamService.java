package com.iesports.dao.service;

import java.util.List;
import com.iesports.model.Team;

public interface ITeamService {

	public List<Team> getTeams();

	public Team getTeam(Long idTeam);

	public Team saveTeam(Team team);

	public Team updateTeam(Team team);

	public void deleteTeam(Team team);
	
	
	
}
