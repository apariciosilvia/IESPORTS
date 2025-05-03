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

	@SuppressWarnings("deprecation")
	@Override
	public Person getPersonById(Long idPerson) {
//		pr.findById(idPerson).orElse(null); REVISAR
		return pr.getById(idPerson);
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
