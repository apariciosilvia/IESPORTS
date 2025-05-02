package com.iesports.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesports.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
