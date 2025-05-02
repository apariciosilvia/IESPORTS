package com.iesports.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iesports.model.Course;

//Posible error, estoy utilizando Integer y en curso int
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
