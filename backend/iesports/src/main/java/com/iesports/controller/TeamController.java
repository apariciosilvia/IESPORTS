package com.iesports.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
import com.iesports.dao.service.impl.PersonServiceImpl;
import com.iesports.dao.service.impl.TeamServiceImpl;
import com.iesports.dto.TeamAddDTO;
import com.iesports.dto.TeamDeleteDTO;
import com.iesports.dto.TeamInfoDTO;
import com.iesports.dto.TeamUpdateDTO;
import com.iesports.model.Person;
import com.iesports.model.Team;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/team")
@Tag(name = "Gestión de Equipos", description = "Endpoints para la gestión de equipos")
public class TeamController {

    @Autowired
    TeamServiceImpl tr;

    @Autowired
    private PersonServiceImpl ps;
    
    @Autowired
	private MatchServiceImpl ms;

    @Operation(summary = "Obtener todos los equipos con información de deportes asociados")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de equipos con sus deportes",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = TeamInfoDTO.class)))
    )
    @GetMapping("/getTeamsInfo")
    public ResponseEntity<List<TeamInfoDTO>> getTeamsInfo() {
        List<Team> teams = tr.getTeams();
        List<TeamInfoDTO> result = new ArrayList<>();
        for (Team team : teams) {
            TeamInfoDTO currentTeamInfo = new TeamInfoDTO();
            currentTeamInfo.setTeam(team);
            currentTeamInfo.setSports(tr.getSportsByIdTeam(team.getId()));
            result.add(currentTeamInfo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Obtener todos los equipos")
    @ApiResponse(
        responseCode = "200",
        description = "Lista de todos los equipos",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))
    )
    @GetMapping("/getTeams")
    public ResponseEntity<?> getTeams() {
        List<Team> teams = tr.getTeams();
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @Operation(summary = "Eliminar un equipo por ID")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Equipo eliminado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"team\":\"Equipo borrado con éxito\"}")
                }
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "El equipo no fue encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"team\":\"El equipo no existe\"}")
                }
            )
        ),
        @ApiResponse(
            responseCode = "409",
            description = "El equipo está asociado a uno o más partidos en torneos",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"team\":\"El equipo con nombre Raimon Eleven participa en un torneo y no puede ser eliminado.\"}")
                }
            )
        )
    })
    @PostMapping("/deleteTeam")
    public ResponseEntity<?> deleteTeam(@Valid @RequestBody TeamDeleteDTO teamDTO) {
        Optional<Team> teamToDelete = tr.getTeams().stream()
            .filter(team -> team.getId().equals(teamDTO.getId()))
            .findFirst();

        if (teamToDelete.isEmpty()) {
            return ResponseEntity
                .badRequest()
                .body(Map.of("team", "El equipo no existe"));
        }

        // Verificar si participa en partidos que pertenezcan a torneos
        boolean isInTournamentMatch = ms.getMatchs().stream()
            .anyMatch(match ->
                (Objects.equals(match.getTeam1().getId(), teamDTO.getId()) ||
                 Objects.equals(match.getTeam2().getId(), teamDTO.getId())) &&
                match.getTournament() != null
            );

        if (isInTournamentMatch) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("team", "El equipo con nombre " + teamToDelete.get().getName() + " participa en un torneo y no puede ser eliminado."));
        }

        tr.deleteTeam(teamToDelete.get());
        return ResponseEntity.ok(Map.of("team", "Equipo borrado con éxito"));
    }




    @Operation(summary = "Agregar un nuevo equipo")
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Equipo creado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Team.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Errores de validación o jugadores no encontrados",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"jugador_5\":\"El jugador con código 5 no existe en la base de datos\"}")
                }
            )
        ),
        @ApiResponse(
            responseCode = "409",
            description = "El nombre del equipo ya existe",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"team\":\"El nombre del equipo ya existe\"}")
                }
            )
        )
    })
    @PostMapping("/addTeam")
    public ResponseEntity<?> addTeam(@Valid @RequestBody TeamAddDTO teamDTO) {
        Map<String, String> errores = new HashMap<>();
        
        System.err.println(teamDTO.toString());

        boolean exists = tr.getTeams().stream()
            .anyMatch(team -> team.getName().equals(teamDTO.getName()));

        if (exists) {
            errores.put("team", "El nombre del equipo ya existe");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
        }

        List<Person> fullPlayers = new ArrayList<>();
        for (Person player : teamDTO.getPlayers()) {
            Person fullPlayer = ps.getPersonById(player.getId());
            if (fullPlayer == null) {
                errores.put("jugador_" + player.getId(), "El jugador con código " + player.getId() + " no existe en la base de datos");
            } else {
                fullPlayers.add(fullPlayer);
            }
        }

        if (!errores.isEmpty()) {
            return ResponseEntity.badRequest().body(errores);
        }

        Team newTeam = new Team();
        newTeam.setName(teamDTO.getName());
        newTeam.setPlayers(fullPlayers);

        Team savedTeam = tr.saveTeam(newTeam);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeam);
    }

    @Operation(summary = "Actualizar equipo y sus participantes")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Equipo actualizado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Team.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Errores de validación",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"nameTeam\":\"El nombre Inazuma Eleven ya esta asignado a un equipo.\"}")
                }
            )
        )
    })
    @PostMapping("/updateTeam")
    public ResponseEntity<?> updateTeam(@Valid @RequestBody TeamUpdateDTO teamUpdateDTO) {

        if (tr.existsNameTeam(teamUpdateDTO.getNameTeam(), teamUpdateDTO.getIdTeam())) {
            System.out.println("El nombre " + teamUpdateDTO.getNameTeam() + " ya esta asignado a un equipo.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("nameTeam", "El nombre " + teamUpdateDTO.getNameTeam() + " ya esta asignado a un equipo."));
        }
        
        Team teamUpdate = new Team(teamUpdateDTO.getIdTeam(), teamUpdateDTO.getNameTeam(), teamUpdateDTO.getPlayers());
        
        tr.updateTeam(teamUpdate);

        return ResponseEntity.status(HttpStatus.OK).body(teamUpdate);
    }
    
    @Operation(summary = "Obtener un equipo por ID")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Equipo encontrado correctamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Team.class),
                examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                    value = "{\"id\":1,\"name\":\"Equipo A\",\"players\":[{\"id\":1,\"name\":\"Jugador 1\"}]}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "El equipo no fue encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                    value = "{\"equipo\":\"El equipo no existe\"}"
                )
            )
        )
    })
    @GetMapping("/getTeamById")
    public ResponseEntity<?> getTeamById(@RequestParam Long idTeam)
    {
    	Map<String, String> errores = new HashMap<>();
    	Team currentTeam = tr.getTeam(idTeam);
    	
    	if(currentTeam == null) {
    		errores.put("equipo", "El equipo no existe");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    	}
    	
    	
    	return ResponseEntity.status(HttpStatus.OK).body(currentTeam);
    }
}
