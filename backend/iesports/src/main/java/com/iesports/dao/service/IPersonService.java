package com.iesports.dao.service;

import java.util.List;

import com.iesports.model.Person;

public interface IPersonService {

	public List<Person> getPersons();

	public Person getPersonById(Long idPerson);

	public Person savePerson(Person person);

	public Person updatePerson(Person person);

	public void deletePerson(Person person);
	
	// CUSTOM QUERYS
	
	public boolean emailExists(String email);

}
