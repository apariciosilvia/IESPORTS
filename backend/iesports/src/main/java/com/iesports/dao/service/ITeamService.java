package com.iesports.dao.service;

import java.util.List;

import com.iesports.model.Sport;
import com.iesports.model.Team;

public interface ITeamService {

	public List<Team> getTeams();

	public Team getTeam(Long idTeam);

	public Team saveTeam(Team team);

	public Team updateTeam(Team team);

	public void deleteTeam(Team team);
	
	public List<Sport> getSportsByIdTeam(Long idTeam);
	
	public List<Team> getTeamsByTournamentId(Long tournamentId);
	
	public boolean existsNameTeam(String nameTeam, Long idTeam);
}
