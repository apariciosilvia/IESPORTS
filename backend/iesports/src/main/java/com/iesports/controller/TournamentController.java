package com.iesports.controller;

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

import com.iesports.dao.service.impl.TournamentServiceImpl;
import com.iesports.dto.TournamentFilterDTO;
import com.iesports.model.Tournament;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

	@Autowired
	private TournamentServiceImpl ts;
	
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
}
