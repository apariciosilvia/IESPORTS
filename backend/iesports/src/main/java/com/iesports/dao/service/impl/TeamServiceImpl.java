package com.iesports.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesports.dao.repository.TeamRepository;
import com.iesports.dao.service.ITeamService;
import com.iesports.model.Sport;
import com.iesports.model.Team;

@Service
public class TeamServiceImpl implements ITeamService{
	
	@Autowired
	TeamRepository tr;

	@Override
	public List<Team> getTeams() {
		return tr.findAll();
	}

	@Override
	public Team getTeam(Long idTeam) {
		return tr.findById(idTeam).orElse(null);
	}

	@Override
	public Team saveTeam(Team team) {
		return tr.save(team);
	}

	@Override
	public Team updateTeam(Team team) {
		// TODO Auto-generated method stub
		return tr.save(team);
	}

	@Override
	public void deleteTeam(Team team) {
		tr.delete(team);
	}

	@Override
	public List<Sport> getSportsByIdTeam(Long idTeam) {
		return tr.getSportsByIdTeam(idTeam);
	}

}
