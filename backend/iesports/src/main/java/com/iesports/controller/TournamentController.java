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

import com.iesports.dao.service.impl.SportServiceImpl;
import com.iesports.dao.service.impl.TeamServiceImpl;
import com.iesports.dao.service.impl.TournamentServiceImpl;
import com.iesports.dto.TournamentAdminDTO;
import com.iesports.dto.TournamentDTO;
import com.iesports.dto.TournamentFilterDTO;
import com.iesports.enums.StateTournamentEnum;
import com.iesports.model.Sport;
import com.iesports.model.Team;
import com.iesports.model.Tournament;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

	@Autowired
	private TournamentServiceImpl ts;
	
	@Autowired
	private TeamServiceImpl teamS;
	
	@Autowired
	private SportServiceImpl ss;
	
	@PostMapping("/getTournaments")
	public ResponseEntity<?> getTournaments(@RequestBody TournamentFilterDTO filterDTO) {
	    Integer sport_id = filterDTO.getSport_id();
	    String date = filterDTO.getDate();

	    List<Tournament> tournaments = null;
	    Map<String, String> errores = new HashMap<>();

	    System.out.println("Parámetros recibidos - sport_id: " + sport_id + ", date: " + date);

	    if (sport_id != null && date != null) {
	        System.out.println("Buscando torneos por sport_id y fecha");
	        tournaments = ts.findTournamentsBySportIdAndDate(sport_id, date);
	    } else if (sport_id != null) {
	        System.out.println("Buscando torneos por sport_id");
	        tournaments = ts.findTournamentsBySportId(sport_id);
	    } else if (date != null) {
	        System.out.println("Buscando torneos por fecha");
	        tournaments = ts.findTournamentsByDate(date);
	    } else {
	        System.out.println("Obteniendo todos los torneos");
	        tournaments = ts.getTournaments();

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
	
	@PostMapping("/addTournament")
	public ResponseEntity<?> addTournament(@Valid @RequestBody TournamentDTO tournamentDTO){
		int resultado = ts.countTournamentsBySportIdAndDate(tournamentDTO.getSportId(), tournamentDTO.getDate());
		
		Map<String, String> errores = new HashMap<>();
		
		System.out.println(tournamentDTO.toString());
		
		//En caso de que haya un torneo del mismo deporte en el mismo año salta error
		if(resultado > 0)
		{
			errores.put("error", "Ya hay un torneo del mismo deporte creado este año");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}
		
		//Si el administrador escoge el número de equipos máximos distintos a 4,8,16 saltará error
		if(tournamentDTO.getMaxTeams() != 4 && tournamentDTO.getMaxTeams() != 8 && tournamentDTO.getMaxTeams() != 16)
		{
			errores.put("error", "Tienes que escoger un rango de equipos máximos correcta");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}
		
		Sport currentSport = ss.getSportById(tournamentDTO.getSportId());
		
		
		//Si el administrador escoge el número de equipos máximos distintos a 4,8,16 saltará error
		if(currentSport == null)
		{
			errores.put("error", "Deporte no válido");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errores);
		}
		
		
		Tournament newTournament = new Tournament(null ,tournamentDTO.getName(), tournamentDTO.getDate(),StateTournamentEnum.PENDIENTE,tournamentDTO.getMaxTeams(),currentSport);
		
		ts.saveTournament(newTournament);
		return ResponseEntity.status(HttpStatus.CREATED).body(newTournament);
	}


	@GetMapping("/getYears")
	public ResponseEntity<?> getDates() {
	    System.out.println("Solicitud recibida para obtener fechas de torneos");

	    List<String> fechas = ts.findTournamentsDates();

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
		
		List<Tournament> tournaments = ts.getTournaments();
		
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
}
