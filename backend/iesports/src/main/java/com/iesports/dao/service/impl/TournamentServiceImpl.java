package com.iesports.dao.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesports.dao.repository.TournamentRepository;
import com.iesports.dao.service.ITournamentService;
import com.iesports.model.Team;
import com.iesports.model.Tournament;

@Service
public class TournamentServiceImpl implements ITournamentService{
	
	@Autowired
	private TournamentRepository tr;

	@Override
	public List<Tournament> getTournaments() {
	    return tr.findAll(); 
	}

	@Override
	public Tournament getTournamentById(Long idTournament) {
		return tr.findById(idTournament).orElse(null);
	}

	@Override
	public Tournament saveTournament(Tournament tournament) {
		return tr.save(tournament);
	}

	@Override
	public Tournament updateTournament(Tournament tournament) {
		return tr.save(tournament);
	}

	@Override
	public void deleteTournament(Tournament tournament) {
		tr.delete(tournament);
	}

	@Override
	public List<Tournament> findTournamentsBySportId(Integer sport_id) {
		return tr.findTournamentsBySportId(sport_id);
	}

	@Override
	public List<Tournament> findTournamentsByDate(String date) {
		return tr.findTournamentsByDate(date);
	}

	@Override
	public List<Tournament> findTournamentsBySportIdAndDate(Integer sport_id, String date) {
		return tr.findTournamentsBySportIdAndDate(sport_id, date);
	}

	@Override
	public List findTournamentsDates() {
		return tr.getTournamentsDates();
	}

	@Override
	public int countTournamentsBySportIdAndDate(Long sport_id, String date) {
		return tr.countTournamentsBySportIdAndDate(date, sport_id);
	}
}
