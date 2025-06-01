package com.iesports.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iesports.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{

	@Query(value = "SELECT * FROM matches WHERE matches.tournament_id = ?1", nativeQuery = true)
	List<Match> getMatchesByTournamentId(Long matchId);
}
