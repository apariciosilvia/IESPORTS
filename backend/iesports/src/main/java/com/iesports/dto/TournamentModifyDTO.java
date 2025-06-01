package com.iesports.dto;

import java.util.List;

import com.iesports.model.Sport;

public class TournamentModifyDTO {

	private long tournamentId;
	private String tournamentNameModified;
	private Sport sportModified;
	private List<MatchModifyTournamentDTO> matches;
	
	
	
	public TournamentModifyDTO(long tournamentId, String tournamentNameModified, Sport sportModified,
			List<MatchModifyTournamentDTO> matches) {
		super();
		this.tournamentId = tournamentId;
		this.tournamentNameModified = tournamentNameModified;
		this.sportModified = sportModified;
		this.matches = matches;
	}
	
	public long getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(long tournamentId) {
		this.tournamentId = tournamentId;
	}
	public String getTournamentNameModified() {
		return tournamentNameModified;
	}
	public void setTournamentNameModified(String tournamentNameModified) {
		this.tournamentNameModified = tournamentNameModified;
	}
	public Sport getSportModified() {
		return sportModified;
	}
	public void setSportModified(Sport sportModified) {
		this.sportModified = sportModified;
	}
	public List<MatchModifyTournamentDTO> getMatches() {
		return matches;
	}
	public void setMatches(List<MatchModifyTournamentDTO> matches) {
		this.matches = matches;
	}
	
	
	
}
