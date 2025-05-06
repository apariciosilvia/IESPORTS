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

	public void addPerson(String name, String email, String password, int course_id) {
		pr.insertUser(name, email, password, course_id);
	}
	
	
}
