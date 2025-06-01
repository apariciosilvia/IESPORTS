package com.iesports.dao.service;

import java.util.List;

import com.iesports.model.Match;
import com.iesports.model.Tournament;

public interface ITournamentService {

	public List<Tournament> getTournaments();

	public Tournament getTournamentById(Long idTournament);

	public Tournament saveTournament(Tournament tournament);

	public Tournament updateTournament(Tournament tournament);

	public void deleteTournament(Tournament tournament);
	
	public List<Tournament> findTournamentsBySportId(Integer sport_id);
	
	public List<Tournament> findTournamentsByDate(String date);
	
	public List<Tournament> findTournamentsBySportIdAndDate(Integer sport_id, String date);
	
	public List<Tournament> findTournamentsDates();
	
	public List<Match> getMatchesByTournamentId(Long id);
	
}
