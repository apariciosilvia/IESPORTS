package com.iesports.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesports.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
