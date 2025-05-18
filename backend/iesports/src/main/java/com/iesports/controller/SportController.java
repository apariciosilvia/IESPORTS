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
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.SportServiceImpl;
import com.iesports.dto.SportRegisterDTO;
import com.iesports.model.Sport;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Sport")
public class SportController {
	
	@Autowired
	private SportServiceImpl ss;
	
	@GetMapping("/get-sports")
	public ResponseEntity<?> getAllSports(){
		return ResponseEntity.ok(ss.getSports());
	}
	
	@PostMapping("/add-sport")
	public ResponseEntity<?> addSports(@Valid @RequestBody SportRegisterDTO Sport){
		
		Map<String, String> errors = new HashMap<>();
		
		System.out.println("Deporte de entrada: " + Sport.toString());
		
		if(ss.sportExists(Sport.getName())) {
			System.err.println("Deporte ya existente");
			errors.put("Existing sport", "Deporte ya existente");
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
}
