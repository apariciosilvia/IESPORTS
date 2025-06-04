package com.iesports.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iesports.model.Sport;
import com.iesports.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query(value = """
			SELECT DISTINCT sports.* FROM matches
			INNER JOIN tournaments ON matches.tournament_id = tournaments.id
			INNER JOIN sports ON tournaments.sport_id = sports.id
			WHERE team1_id = ?1 OR team2_id = ?1
			""", nativeQuery = true)
	public List<Sport> getSportsByIdTeam(Long idTeam);
	
	@Query(value = """
			SELECT DISTINCT teams.*
			FROM teams
			INNER JOIN (
			    SELECT team1_id AS team_id, tournament_id FROM matches
			    UNION
			    SELECT team2_id AS team_id, tournament_id FROM matches
			) AS all_matches ON all_matches.team_id = teams.id
			INNER JOIN tournaments ON all_matches.tournament_id = tournaments.id
			WHERE tournaments.id = ?1;
			""", nativeQuery = true)
	public List<Team> getTeamsByTournamentId(Long tournamentId);
	
	
	@Query(value = "SELECT * FROM TEAM WHERE id=?1 AND name=?2", nativeQuery = true)
	public Team getTeamByIdAndName(Long id, String name);
}
