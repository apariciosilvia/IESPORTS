package com.iesports.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.MatchServiceImpl;
import com.iesports.dao.service.impl.SportServiceImpl;
import com.iesports.dao.service.impl.TeamServiceImpl;
import com.iesports.dao.service.impl.TournamentServiceImpl;
import com.iesports.dto.TournamentAddDTO;
import com.iesports.dto.TournamentAdminDTO;
import com.iesports.dto.TournamentFilterDTO;
import com.iesports.enums.RoundMatchEnum;
import com.iesports.enums.StateTournamentEnum;
import com.iesports.model.Match;
import com.iesports.model.Sport;
import com.iesports.model.Team;
import com.iesports.model.Tournament;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

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
	@Autowired
	SportServiceImpl sportS;
	
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
	public ResponseEntity<?> addTournament(@Valid @RequestBody  TournamentAddDTO tournamentDTO){
		
		String currentYear = null;
		Map<String, String> errores = new HashMap<>();
        
		
		if(tournamentDTO.getSportId() == 0 || tournamentDTO.getSportId().toString() == null || tournamentDTO.getSportId().toString().isEmpty())
		{
			errores.put("error", "No se ha encontrado el deporte");
			System.err.println("No se ha encontrado el deporte");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}
		
		if (tournamentDTO.getNumTeams() != 4 && tournamentDTO.getNumTeams() != 8 && tournamentDTO.getNumTeams() != 16)
		{
			errores.put("error", "El número de equipos es incorrecto");
			System.err.println("El número de equipos es incorrecto");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}
		
		//EN CASO DE ESTAR EN EL RANGO DE SEPTIEMBRE-DICIEMBRE, EL AÑO QUE SE GENERA
		//SEGUIRÁ LA SIGUIENTE ESTRUCTURA --> año actual/año siguiente
		if(LocalDate.now().getMonthValue() >= 9 && LocalDate.now().getMonthValue() <= 12)
		{
			String currentNextYear = String.valueOf((LocalDate.now().getYear()%100)+1);
			currentYear = String.valueOf(LocalDate.now().getYear())+"/"+currentNextYear;
		}
		//EN CASO CONTRARIO UTILIZARÁ ESTA ESTRUCTURA --> AÑO PASADO/AÑO ACTUAL
		else
		{
			String currentNextYear = String.valueOf((LocalDate.now().getYear()%100));
			currentYear = String.valueOf(LocalDate.now().getYear()-1)+"/"+currentNextYear;
		}
		
		Sport currentSport = sportS.getSportById(tournamentDTO.getSportId());
		Tournament currentTournament = new Tournament(null,tournamentDTO.getName(),currentYear, StateTournamentEnum.PENDIENTE, currentSport);
		
		//Primero añadimos el nuevo torneo
		currentTournament = tournamentS.saveTournament(currentTournament);
		
		System.out.println("SE HA GUARDADO EL TORNEO"+currentTournament);
		
		//Calculamos el número de partidos
		int numMatches = tournamentDTO.getNumTeams() / 2;
		
		if(tournamentDTO.getNumTeams() == 4)
			currentRoundState = RoundMatchEnum.SEMIFINAL;
		if(tournamentDTO.getNumTeams() == 8)
			currentRoundState = RoundMatchEnum.CUARTOS_FINAL;
		if(tournamentDTO.getNumTeams() == 16)
			currentRoundState = RoundMatchEnum.OCTAVOS;
		
		for (int i = 0; i < numMatches; i++)
		{
			Team currentTeam1 = teamS.getTeam(tournamentDTO.getMatches().get(i).getTeam1Id());
			Team currentTeam2 = teamS.getTeam(tournamentDTO.getMatches().get(i).getTeam2Id());
			
			Match currentMatch = new Match(null, tournamentDTO.getMatches().get(i).getMatchDate(), currentRoundState, currentTournament, currentTeam1,currentTeam2 , 0, 0 ,null);
			
			currentMatch = matchS.saveMatch(currentMatch);
			System.out.println("SE HA GUARDADO EL PARTIDO" + currentMatch);
		}
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(currentTournament);
	}
	
	@GetMapping("/getTournamentById")
	@Operation(
	    summary = "Obtener torneo por ID",
	    description = "Devuelve el torneo correspondiente al ID proporcionado"
	)
	public ResponseEntity<Tournament> getTournamentById(@RequestParam Long tournamentId) {
	    Tournament tournament = tournamentS.getTournamentById(tournamentId);

	    if (tournament == null) {
	        System.err.println("No se encontro el torneo con el id " + tournamentId);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tournament);
	    }

	    return ResponseEntity.status(HttpStatus.OK).body(tournament);
	}
	
	
//	@PostMapping("/modifyTournament")
//	public ResponseEntity<?> modifyTournament(@Valid @RequestBody TournamentModifyDTO tournamentDTO ){
//		
//		
//		
//		return null;
//	}
	
}
