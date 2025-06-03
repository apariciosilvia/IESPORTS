package com.iesports.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iesports.dao.repository.MatchRepository;
import com.iesports.dao.service.IMatchService;
import com.iesports.enums.RoundMatchEnum;
import com.iesports.model.Match;

@Service
public class MatchServiceImpl implements IMatchService{

	@Autowired
	private MatchRepository mr;
	
	@Override
	public List<Match> getMatchs() {
		return mr.findAll();
	}

	@Override
	public Match getMatchById(Long idMatch) {
		// TODO Auto-generated method stub
		return mr.findById(idMatch).orElse(null);
	}

	@Override
	public Match saveMatch(Match match) {
		// TODO Auto-generated method stub
		return mr.save(match);
	}

	@Override
	public Match updateMatch(Match match) {
		return mr.save(match);
	}

	@Override
	public void deleteMatch(Match match) {
		mr.delete(match);
	}

	@Override
	public List<Match> getMatchesByTournamentId(Long matchId) {
		return mr.getMatchesByTournamentId(matchId);
	}

	@Override
	public int countMatchByTeamIdTournamentIdAndStateRound(Long matchId, Long tournamentId, RoundMatchEnum round) {
		return mr.countMatchByTeamIdTournamentIdAndStateRound(matchId, tournamentId, round);
	}
	
	

}
