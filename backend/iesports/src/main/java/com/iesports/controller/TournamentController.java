package com.iesports.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.MatchServiceImpl;
import com.iesports.dao.service.impl.TeamServiceImpl;
import com.iesports.dao.service.impl.TournamentServiceImpl;
import com.iesports.dto.TournamentAddDTO;
import com.iesports.dto.TournamentAdminDTO;
import com.iesports.dto.TournamentFilterDTO;
import com.iesports.enums.RoundMatchEnum;
import com.iesports.model.Match;
import com.iesports.model.Team;
import com.iesports.model.Tournament;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

	RoundMatchEnum currentRoundState = null;
	@Autowired
	private TournamentServiceImpl tournamentS;
	
	@Autowired
	private MatchServiceImpl matchS;
	
	@Autowired
	TeamServiceImpl teamS;
	
	@PostMapping("/getTournaments")
	public ResponseEntity<?> getTournaments(@RequestBody TournamentFilterDTO filterDTO) {
	    Integer sport_id = filterDTO.getSport_id();
	    String date = filterDTO.getDate();

	    List<Tournament> tournaments = null;
	    Map<String, String> errores = new HashMap<>();

	    System.out.println("Parámetros recibidos - sport_id: " + sport_id + ", date: " + date);

	    if (sport_id != null && date != null) {
	        System.out.println("Buscando torneos por sport_id y fecha");
	        tournaments = tournamentS.findTournamentsBySportIdAndDate(sport_id, date);
	    } else if (sport_id != null) {
	        System.out.println("Buscando torneos por sport_id");
	        tournaments = tournamentS.findTournamentsBySportId(sport_id);
	    } else if (date != null) {
	        System.out.println("Buscando torneos por fecha");
	        tournaments = tournamentS.findTournamentsByDate(date);
	    } else {
	        System.out.println("Obteniendo todos los torneos");
	        tournaments = tournamentS.getTournaments();

	        tournaments = tournaments.stream()
	                .sorted((t1, t2) -> t2.getDate().compareTo(t1.getDate()))
	                .collect(Collectors.toList());

	        System.out.println("Torneos ordenados de más nuevo a más viejo");
	    }

	    System.out.println("Cantidad de torneos encontrados: " + (tournaments != null ? tournaments.size() : 0));

	    if (tournaments == null) {
	        System.err.println("No se encontraron torneos");
	        errores.put("error", "No se encontraron torneos con los parámetros indicados");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
	    }

	    return ResponseEntity.ok(tournaments);
	}


	@GetMapping("/getYears")
	public ResponseEntity<?> getDates() {
	    System.out.println("Solicitud recibida para obtener fechas de torneos");

	    List<String> fechas = tournamentS.findTournamentsDates();

	    if (fechas == null || fechas.isEmpty()) {
	        System.err.println("No se encontraron fechas de torneos");
	        Map<String, String> errores = new HashMap<>();
	        errores.put("error", "No se encontraron fechas de torneos");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
	    }

	    System.out.println("Se encontraron " + fechas.size() + " fechas de torneos");
	    return ResponseEntity.ok(fechas);
	}
	
	@GetMapping("/getTeamsByTournamentId")
	public ResponseEntity<?> getTeamsByTournamentId(){
		List<TournamentAdminDTO> result = new ArrayList<TournamentAdminDTO>();
		
		List<Tournament> tournaments = tournamentS.getTournaments();
		
		for (Tournament currentTournament : tournaments)
		{
			TournamentAdminDTO newTournamentAdmin = new TournamentAdminDTO();
			List<Team> teamsCurrentTournament = teamS.getTeamsByTournamentId(currentTournament.getId());
			
			newTournamentAdmin.setTournament(currentTournament);
			newTournamentAdmin.setTeams(teamsCurrentTournament);
			
			
			
			result.add(newTournamentAdmin);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping("/addTournament")
	public ResponseEntity<?> addTournament(@RequestBody TournamentAddDTO tournamentDTO){
		
		Tournament currentTournament = new Tournament(null,tournamentDTO.getTournament().getName(),
										tournamentDTO.getTournament().getDate(),tournamentDTO.getTournament().getState(),tournamentDTO.getTournament().getSport(),
										tournamentDTO.getTournament().getMaxTeams());
		
		
		//Primero añadimos el nuevo torneo
		tournamentS.saveTournament(currentTournament);
		
		//Calculamos el número de partidos
		int numMatches = tournamentDTO.getTournament().getMaxTeams() / 2;
		
		if(tournamentDTO.getTournament().getMaxTeams() == 4)
			currentRoundState = RoundMatchEnum.SEMIFINAL;
		if(tournamentDTO.getTournament().getMaxTeams() == 8)
			currentRoundState = RoundMatchEnum.CUARTOS_FINAL;
		if(tournamentDTO.getTournament().getMaxTeams() == 16)
			currentRoundState = RoundMatchEnum.OCTAVOS;
		
		
		for (int i = 0; i < numMatches; i++)
		{
			Match currentMatch = new Match(null, tournamentDTO.getMatches().get(i).getDate(), currentRoundState, currentTournament,
					tournamentDTO.getMatches().get(i).getTeam1(), tournamentDTO.getMatches().get(i).getTeam2(), 0, 0, null);
			
			matchS.saveMatch(currentMatch);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(currentTournament);
	}
	
	
	
}
