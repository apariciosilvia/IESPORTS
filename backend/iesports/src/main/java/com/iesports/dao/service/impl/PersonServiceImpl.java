package com.iesports.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesports.dao.repository.PersonRepository;
import com.iesports.dao.service.IPersonService;
import com.iesports.model.Person;

@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private PersonRepository pr;

	@Override
	public List<Person> getPersons() {
		return pr.findAll();
	}

	@Override
	public Person getPersonById(Long idPerson) {
		return pr.findById(idPerson).orElse(null);
	}

	@Override
	public Person savePerson(Person person) {
		return pr.save(person);
	}

	@Override
	public Person updatePerson(Person person) {
		return pr.save(person);
	}

	@Override
	public void deletePerson(Person person) {
		pr.delete(person);
	}
	
	// CUSTOM QUERYS

	@Override
	public boolean emailExists(String email) {
		
//		boolean emailExist = true;
//		int coincidencesEmail = pr.emailExists(email);
//		
//		if (coincidencesEmail != 0) {
//			// No existe
//			emailExist = false;
//		}
		
		return pr.emailExists(email) != 0;
	}

	@Override
	public Person getPerson(String email, String password) {
		return pr.personExists(email, password);
	}
	
//	public List<Person> getPersons(){
//		return pr.findAll();
//	}

//	public void addPerson(String name, String email, String password, int course_id) {
//		pr.insertUser(name, email, password, course_id);
//	}
	
	
}
