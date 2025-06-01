package com.iesports.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iesports.model.Match;
import com.iesports.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{

	@Query(value = "SELECT * FROM tournaments WHERE sport_id = ?1 ORDER by date DESC", nativeQuery = true)
	List<Tournament> findTournamentsBySportId(Integer sport_id);
	
	@Query(value = "SELECT * FROM tournaments WHERE date = ?1 ORDER by date DESC", nativeQuery = true)
	List<Tournament> findTournamentsByDate(String date);
	
	@Query(value = "SELECT * FROM tournaments WHERE sport_id = ?1 and date =?2 ORDER by date DESC", nativeQuery = true)
	List<Tournament> findTournamentsBySportIdAndDate(Integer sport_id, String date);
	
	@Query(value = "SELECT DISTINCT date FROM tournaments ORDER by date DESC", nativeQuery = true)
	List<String> getTournamentsDates();
	
	@Query(value = "SELECT * FROM matches WHERE matches.tournament_id = ?1", nativeQuery = true)
	List<Match> getMatchesByTournamentId(Long id);
	
}
