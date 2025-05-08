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
	
//	public List<Person> getPersons(){
//		return pr.findAll();
//	}

//	public void addUser(int id, Course course_id, String name, String email, String password, int activo) {
//		/*Rol default_alumno = new Rol();
//		default_alumno.setId(4);
//		Persona persona = new Persona( 0, curso_id, nombre, email, password, 1, );
//	    pr.save(persona);*/
//		
//	}
	
	
}
