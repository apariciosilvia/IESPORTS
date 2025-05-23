package com.iesports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.MatchServiceImpl;
import com.iesports.model.Match;

@RestController
@RequestMapping ("/match")
public class MatchController {
	
	@Autowired
	private MatchServiceImpl ms;
	
	@GetMapping("/getMatches")
	public ResponseEntity<List<Match>> getMatches(){
		List<Match> matches = ms.getMatchs();
		
		if (matches.isEmpty()){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(matches);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(matches);
	}

}
