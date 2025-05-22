package com.iesports.dto;

import com.iesports.model.Sport;

public class TournamentDTO {
	
	private String name;
	private String date;
	private Sport sport;
	private int max_equipos;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	public int getMax_equipos() {
		return max_equipos;
	}
	public void setMax_equipos(int max_equipos) {
		this.max_equipos = max_equipos;
	}
}
