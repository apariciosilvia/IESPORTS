package com.iesports.dao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iesports.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	@Query(value = "SELECT COUNT(*) FROM persons WHERE email = ?1", nativeQuery = true)
	Integer emailExists(String email);

	@Query(value = "SELECT * FROM persons WHERE email = ?1", nativeQuery = true)
	Person getPersonByEmail(String email);

	@Query(value = "SELECT * FROM persons WHERE email = ?1 AND password = ?2", nativeQuery = true)
	Person personExists( String email, String password);

}
