package com.iesports.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iesports.dao.service.impl.PersonServiceImpl;
import com.iesports.dao.service.impl.TeamServiceImpl;
import com.iesports.dto.TeamAddDTO;
import com.iesports.dto.TeamDeleteDTO;
import com.iesports.dto.TeamInfoDTO;
import com.iesports.dto.TeamUpdateDTO;
import com.iesports.model.Person;
import com.iesports.model.Team;

import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/team")
@Tag(name = "Gestión de Equipos", description = "Endpoints para la gestión de equipos")
public class TeamController {

    @Autowired
    TeamServiceImpl tr;

    @Autowired
    private PersonServiceImpl ps;

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
        )
    })
    @PostMapping("/deleteTeam")
    public ResponseEntity<?> deleteTeam(@Valid @RequestBody TeamDeleteDTO teamDTO) {
        Optional<Team> teamToDelete = tr.getTeams().stream()
            .filter(team -> team.getId().equals(teamDTO.getId()))
            .findFirst();

        if (teamToDelete.isPresent()) {
            tr.deleteTeam(teamToDelete.get());
            return ResponseEntity.ok(Map.of("team", "Equipo borrado con éxito"));
        }

        Map<String, String> errores = Map.of("team", "El equipo no existe");
        return ResponseEntity.badRequest().body(errores);
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

    @Operation(summary = "Actualizar los jugadores de un equipo existente")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Equipo actualizado correctamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Team.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Errores de validación o equipo no encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class),
                examples = {
                    @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"team\":\"El equipo no existe\", \"jugador_7\":\"El jugador con código 7 no existe\"}")
                }
            )
        )
    })
    @PostMapping("/updateTeam")
    public ResponseEntity<?> updateTeam(@Valid @RequestBody TeamUpdateDTO teamDTO) {
        Map<String, String> errores = new HashMap<>();

        Optional<Team> existingTeamOpt = tr.getTeams().stream()
            .filter(team -> team.getName().equals(teamDTO.getName()))
            .findFirst();

        if (existingTeamOpt.isEmpty()) {
            errores.put("team", "El equipo no existe");
            return ResponseEntity.badRequest().body(errores);
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

        Team existingTeam = existingTeamOpt.get();
        existingTeam.setPlayers(fullPlayers);
        Team updatedTeam = tr.saveTeam(existingTeam);

        return ResponseEntity.ok(updatedTeam);
    }
    
    @Operation(summary = "Obtener un equipo por ID y nombre")
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
    @GetMapping("/getTeamByIdAndName")
    public ResponseEntity<?> getTeamByIdAndName(@RequestParam Long id, @RequestParam String name)
    {
    	Map<String, String> errores = new HashMap<>();
    	Team currentTeam = tr.getTeamByIdAndName(id, name);
    	
    	if(currentTeam == null) {
    		errores.put("equipo", "El equipo no existe");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    	}
    	
    	
    	return ResponseEntity.status(HttpStatus.OK).body(currentTeam);
    }
}
