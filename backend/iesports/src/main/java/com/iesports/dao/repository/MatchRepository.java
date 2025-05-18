package com.iesports.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesports.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{

}
