package com.iesports.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iesports.model.Sport;

public interface SportRepository extends JpaRepository<Sport, Long>{

	@Query(value = "SELECT COUNT(*) FROM sports WHERE name = ?1", nativeQuery = true)
	public int sportExists(String name);

}
