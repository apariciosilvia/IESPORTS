package com.iesports.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iesports.dao.service.impl.TeamServiceImpl;
import com.iesports.dao.service.impl.TournamentServiceImpl;
import com.iesports.dto.TeamInfoDTO;
import com.iesports.dto.TournamentAdminDTO;
import com.iesports.model.Team;
import com.iesports.model.Tournament;

@RestController
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	TeamServiceImpl tr;
	
	@Autowired
	TournamentServiceImpl tournamentS;
	
	@GetMapping("/getTeamsInfo")
	public ResponseEntity<List<TeamInfoDTO>> getTeamsInfo(){
		List<Team> teams = tr.getTeams();
		List<TeamInfoDTO> result = new ArrayList<>();
		for (Team team : teams){
			
			TeamInfoDTO currentTeamInfo = new TeamInfoDTO();
			
			currentTeamInfo.setTeam(team);
			currentTeamInfo.setSports(tr.getSportsByIdTeam(team.getId()));
			
			
			result.add(currentTeamInfo);
			
			
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/getTeamsByTournamentId")
	public ResponseEntity<?> getTeamsByTournamentId(){
		List<TournamentAdminDTO> result = new ArrayList<TournamentAdminDTO>();
		
		List<Tournament> tournaments = tournamentS.getTournaments();
		
		for (Tournament currentTournament : tournaments)
		{
			TournamentAdminDTO newTournamentAdmin = new TournamentAdminDTO();
			List<Team> teamsCurrentTournament = tr.getTeamsByTournamentId(currentTournament.getId());
			
			newTournamentAdmin.setTournament(currentTournament);
			newTournamentAdmin.setTeams(teamsCurrentTournament);
			
			
			
			result.add(newTournamentAdmin);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

}
