package com.iesports.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iesports.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	
	@Query(value = "SELECT COUNT(*) FROM persons WHERE email = ?1", nativeQuery = true)
	Integer emailExists(String email);
}
