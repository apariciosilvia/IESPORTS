package com.iesports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.CourseServiceImpl;
import com.iesports.model.Course;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

@RestController
@RequestMapping("/course")
@Tag(name = "Gestión de cursos", description = "Endpoints para la gestión de cursos")
public class CourseController {

	@Autowired
	private CourseServiceImpl cs;

	@Operation(summary = "Obtener la lista de todos los cursos")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Lista de cursos obtenida correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            array = @ArraySchema(schema = @Schema(implementation = Course.class)),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "[{\"id\":1,\"name\":\"Matemáticas\"}, {\"id\":2,\"name\":\"Historia\"}]"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "204",
	        description = "No hay cursos disponibles",
	        content = @Content
	    )
	})
	@GetMapping("/getCourses")
	public ResponseEntity<List<Course>> getCourses() {
		
		List<Course> courses = cs.getCourses();
		
		if (courses.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courses);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(courses);
	}

}
