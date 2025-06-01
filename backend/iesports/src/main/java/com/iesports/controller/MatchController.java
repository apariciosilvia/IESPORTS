package com.iesports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.MatchServiceImpl;
import com.iesports.model.Match;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping ("/match")
public class MatchController {
	
	@Autowired
	private MatchServiceImpl ms;
	
	@GetMapping("/getMatches")
	public ResponseEntity<List<Match>> getMatches(){
		List<Match> matches = ms.getMatchs();
		
		if (matches.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(matches);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(matches);
	}
	
	@GetMapping("/getMatchesByTournamentId")
	@Operation(
	    summary = "Obtener partidos por ID de torneo",
	    description = "Devuelve la lista de partidos asociados al torneo indicado por su identificador"
	)
	public ResponseEntity<List<Match>> getMatchesByTournamentId(@RequestParam Long tournamentId) {
		
		List<Match> matches = ms.getMatchesByTournamentId(tournamentId);

		if (matches.isEmpty()) {
			System.err.println("No se ha encontrado partidos con esta clave id de torneo");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(matches);
		}

		return ResponseEntity.status(HttpStatus.OK).body(matches);
	}

}
