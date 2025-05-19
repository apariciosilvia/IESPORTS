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
}
