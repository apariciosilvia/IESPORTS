package com.iesports.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.SportServiceImpl;
import com.iesports.dto.SportDeleteDTO;
import com.iesports.dto.SportRegisterDTO;
import com.iesports.dto.SportUpdateDTO;
import com.iesports.model.Sport;
import com.iesports.model.Team;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/sport")
@Tag(name = "Gestión de deportes", description = "Endpoints para la gestión de deportes")
public class SportController {
	
	@Autowired
	private SportServiceImpl ss;
	
	 @Operation(summary = "Obtener todos los deportes")
	    @ApiResponse(
	        responseCode = "200",
	        description = "Lista de todos los deportes",
	        content = @Content(mediaType = "application/json",
	            schema = @Schema(implementation = Sport.class))
	    )
	@GetMapping("/getSports")
	public ResponseEntity<?> getAllSports(){
		return ResponseEntity.ok(ss.getSports());
	}
	
	 @Operation(summary = "Agregar un nuevo deporte")
	    @ApiResponses({
	        @ApiResponse(
	            responseCode = "201",
	            description = "Deporte creado correctamente",
	            content = @Content(mediaType = "application/json",
	                schema = @Schema(implementation = Sport.class))
	        ),
	        @ApiResponse(
	            responseCode = "400",
	            description = "Errores de validación o deporte ya existente",
	            content = @Content(mediaType = "application/json",
	                schema = @Schema(implementation = Map.class),
	                examples = {
	                    @ExampleObject(value = "{\"sport\":\"Deporte ya existente\"}")
	                }
	            )
	        )
	    })
	@PostMapping("/addSport")
	public ResponseEntity<?> addSports(@Valid @RequestBody SportRegisterDTO Sport){
		
		Map<String, String> errors = new HashMap<>();
		
		System.out.println("Deporte de entrada: " + Sport.toString());
		
		if(ss.sportExists(Sport.getName())) {
			System.err.println("Deporte ya existente");
			errors.put("sport", "Deporte ya existente");
		}
		
		if (errors.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		System.out.println("Nuevo deporte a añadir: " + Sport.toString());
		
		Sport newSport = new Sport(null,Sport.getName());
		ss.saveSport(newSport);
		
		System.out.println("Nuevo deporte añadido: " + Sport.toString());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newSport);
	}
	 
	 @Operation(summary = "Actualizar un deporte existente")
	 @ApiResponses({
	     @ApiResponse(
	         responseCode = "200",
	         description = "Deporte actualizado correctamente",
	         content = @Content(mediaType = "application/json",
	             schema = @Schema(implementation = Sport.class))
	     ),
	     @ApiResponse(
	         responseCode = "400",
	         description = "El deporte no fue encontrado o el nombre ya está en uso",
	         content = @Content(mediaType = "application/json",
	             schema = @Schema(implementation = Map.class),
	             examples = {
	                 @ExampleObject(value = "{\"sport\":\"El deporte con ID 1 no existe\"}"),
	                 @ExampleObject(value = "{\"sport\":\"El nombre del deporte ya está en uso\"}")
	             }
	         )
	     )
	 })
	 
	 @PostMapping("/updateSport")
	 public ResponseEntity<?> updateSport(@Valid @RequestBody SportUpdateDTO sportDTO) {
	     Map<String, String> errors = new HashMap<>();

	     Sport existingSport = ss.getSportById(sportDTO.getId());
	     if (existingSport == null) {
	         errors.put("sport", "El deporte con ID " + sportDTO.getId() + " no existe");
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	     }

	     if (ss.sportExists(sportDTO.getName()) && !existingSport.getName().equalsIgnoreCase(sportDTO.getName())) {
	         errors.put("sport", "El nombre del deporte ya está en uso");
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	     }

	     existingSport.setName(sportDTO.getName());
	     ss.saveSport(existingSport);

	     return ResponseEntity.ok(existingSport);
	 }
	 
	 @Operation(summary = "Eliminar un deporte por ID")
	 @ApiResponses({
	     @ApiResponse(
	         responseCode = "200",
	         description = "Deporte eliminado correctamente",
	         content = @Content(mediaType = "application/json",
	             schema = @Schema(implementation = Map.class),
	             examples = {
	                 @ExampleObject(value = "{\"sport\":\"Deporte borrado con éxito\"}")
	             }
	         )
	     ),
	     @ApiResponse(
	         responseCode = "400",
	         description = "El deporte no fue encontrado",
	         content = @Content(mediaType = "application/json",
	             schema = @Schema(implementation = Map.class),
	             examples = {
	                 @ExampleObject(value = "{\"sport\":\"El deporte con ID 1 no existe\"}")
	             }
	         )
	     )
	 })
	 
	 @PostMapping("/deleteSport")
	 public ResponseEntity<?> deleteSport(@Valid @RequestBody SportDeleteDTO sportDTO) {
	     Map<String, String> errors = new HashMap<>();

	     Sport existingSport = ss.getSportById(sportDTO.getId());
	     if (existingSport == null) {
	         errors.put("sport", "El deporte con ID " + sportDTO.getId() + " no existe");
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	     }
	     ss.deleteSport(existingSport);
         return ResponseEntity.ok(Map.of("sport", "Deporte borrado con éxito"));
	 }
	 
	 @Operation(summary = "Obtener un deporte por ID")
	 @ApiResponses({
	     @ApiResponse(
	         responseCode = "200",
	         description = "Deporte obtenido correctamente",
	         content = @Content(mediaType = "application/json",
	             schema = @Schema(implementation = Sport.class),
	             examples = {
	                 @ExampleObject(value = "{\"id\":1,\"name\":\"Fútbol\"}")
	             }
	         )
	     ),
	     @ApiResponse(
	         responseCode = "400",
	         description = "El deporte no fue encontrado",
	         content = @Content(mediaType = "application/json",
	             schema = @Schema(implementation = Map.class),
	             examples = {
	                 @ExampleObject(value = "{\"sport\":\"El deporte no existe\"}")
	             }
	         )
	     )
	 })
	@GetMapping("/getSportById")
	public ResponseEntity<?> getSportById(@RequestParam Long id){
		Map<String, String> errores = new HashMap<>();
		Sport currentSport = ss.getSportById(id);
		
		if(currentSport == null) {
			errores.put("sport", "El deporte no existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(currentSport);
	}
	 
	 

}
