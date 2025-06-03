package com.iesports.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iesports.enums.RoundMatchEnum;
import com.iesports.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{

	@Query(value = "SELECT * FROM matches WHERE matches.tournament_id = ?1", nativeQuery = true)
	List<Match> getMatchesByTournamentId(Long matchId);
	

	@Query(value = "SELECT COUNT(*) FROM matches WHERE (team1_id = ?1 OR team2_id = ?1) AND tournament_id = ?2 AND round = ?3", nativeQuery = true)
	int countMatchByTeamIdTournamentIdAndStateRound(Long teamId, Long tournamentId, String round);
}
