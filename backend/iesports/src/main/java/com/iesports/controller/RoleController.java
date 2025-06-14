package com.iesports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.RoleServiceImpl;
import com.iesports.model.Role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/role")
@Tag(name = "Gestión de roles", description = "Endpoints para la gestión de roles")
public class RoleController {

	@Autowired
	RoleServiceImpl roleS;
	
	@Operation(summary = "Obtener todos los roles")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Lista de todos los roles",
	        content = @Content(mediaType = "application/json",
	            array = @ArraySchema(schema = @Schema(implementation = Role.class)))
	    ),
	    @ApiResponse(
	        responseCode = "204",
	        description = "Lista vacía de roles",
	        content = @Content(mediaType = "application/json",
	            examples = @ExampleObject(value = "[]"))
	    )
	})
	@GetMapping("/getRoles")
	public ResponseEntity<List<Role>> getRoles() {
	    List<Role> roles = roleS.getRoles();

	    if (roles.isEmpty()) {
	        System.err.println("No se ha encontrado roles en la base de datos.");
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(roles);
	    }

	    return ResponseEntity.status(HttpStatus.OK).body(roles);
	}
}
