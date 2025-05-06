package com.iesports.dao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iesports.model.Person;

import jakarta.transaction.Transactional;

public interface PersonRepository extends JpaRepository<Person, Long>{
	@Modifying
	@Transactional
	@Query
	(value="INSERT INTO persons (name, email, password, course_id, role_id, active) VALUES (:name, :email, :password, :course_id, 4, 0);", nativeQuery = true)
	public void insertUser(@Param("name") String name, @Param("email") String email ,@Param("password") String password ,@Param("course_id") int course_id);

}
