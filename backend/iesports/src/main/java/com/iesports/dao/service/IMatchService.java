package com.iesports.dao.service;

import java.util.List;

import com.iesports.model.Match;


public interface IMatchService {
	
	public List<Match> getMatchs();

	public Match getMatchById(Long idMatch);

	public Match saveMatch(Match match);

	public Match updateMatch(Match match);

	public void deleteMatch(Match match);
	
	List<Match> getMatchesByTournamentId(Long matchId);

}
