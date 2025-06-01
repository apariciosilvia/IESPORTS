package com.iesports.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Lista de partidos devuelta correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Match.class, type = "array")
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Mapa clave-valor con los errores"
	    )
	})
	public ResponseEntity<?> getMatchesByTournamentId(
	    @Parameter(description = "ID del torneo a buscar", required = true)
	    @RequestParam Long tournamentId
	) {
	    Map<String, String> errores = new HashMap<>();
	    List<Match> matches = ms.getMatchesByTournamentId(tournamentId);

	    if (matches.isEmpty()) {
	        errores.put("tournamentId", "no se ha encontrado partidos con esta clave id de torneo");
	        System.err.println("No se ha encontrado partidos con esta clave id de torneo");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
	    }

	    return ResponseEntity.status(HttpStatus.OK).body(matches);
	}

}
