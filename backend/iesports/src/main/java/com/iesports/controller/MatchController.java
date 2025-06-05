package com.iesports.controller;

import java.util.Comparator;
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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping ("/match")
@Tag(name = "Gestión de partidos", description = "Endpoints para la gestión de partidos")
public class MatchController {
	
	@Autowired
	private MatchServiceImpl ms;
	

	@Operation(summary = "Obtener todos los partidos")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Partidos obtenidos correctamente",
	        content = @Content(mediaType = "application/json",
	            schema = @Schema(implementation = Match.class)
	           
	        )
	    ),
	    @ApiResponse(
	        responseCode = "204",
	        description = "No hay partidos disponibles",
	        content = @Content(mediaType = "application/json",
	            examples = {
	                @ExampleObject(value = "[]")
	            }
	        )
	    )
	})
	@GetMapping("/getMatches")
	public ResponseEntity<List<Match>> getMatches(){
		List<Match> matches = ms.getMatchs();
		
		if (matches.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(matches);
		}
		
	    matches.sort(
	    		Comparator
	                .comparing((Match m) -> m.getTournament().getDate(), Comparator.nullsLast(Comparator.reverseOrder()))
	                .thenComparing(m -> m.getTournament().getName(), Comparator.nullsLast(String::compareToIgnoreCase))
	        );
		
		return ResponseEntity.status(HttpStatus.OK).body(matches);
	}
	
	@Operation(summary = "Obtener la lista de partidos")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Partidos obtenidos correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Match.class),
	            examples = @ExampleObject(
	                value = "[{\"id\":1,\"teamA\":\"Equipo A\",\"teamB\":\"Equipo B\",\"date\":\"2024-10-01T18:00:00\"}]"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "204",
	        description = "No hay partidos disponibles",
	        content = @Content(
	            mediaType = "application/json",
	            examples = @ExampleObject(value = "[]")
	        )
	    )
	})
	
	@GetMapping("/getMatchesByTournamentId")
	public ResponseEntity<List<Match>> getMatchesByTournamentId(@RequestParam Long tournamentId) {
		
		List<Match> matches = ms.getMatchesByTournamentId(tournamentId);

		if (matches.isEmpty()) {
			System.err.println("No se ha encontrado partidos con esta clave id de torneo");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(matches);
		}

		return ResponseEntity.status(HttpStatus.OK).body(matches);
	}

}
