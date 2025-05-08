package com.iesports.dao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iesports.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	
	@Query(value = "SELECT COUNT(*) FROM persons WHERE email = ?1", nativeQuery = true)
	Integer emailExists(String email);
	
	//@Modifying
	//@Transactional
	//@Query
	//(value="INSERT INTO persons (name, email, password, course_id, role_id, active) VALUES (:name, :email, :password, :course_id, 4, 0);", nativeQuery = true)
	//public void insertUser(@Param("name") String name, @Param("email") String email ,@Param("password") String password ,@Param("course_id") int course_id);
}
