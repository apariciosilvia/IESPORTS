package com.iesports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.PersonServiceImpl;
import com.iesports.model.Person;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServiceImpl ps;
	
	/*Método que devuelve todos los datos de la BBDD*/
	@GetMapping("/getPersons")
	public List<Person> getPersons(){
		return ps.getPersons();
	}
	
	@PostMapping("/login")
	public Person login(@RequestParam String email, @RequestParam (name = "password") String password) {
		
		List<Person> persons = ps.getPersons();
		Person person = null;
		
		for (Person currentPerson : persons)
		{
			if(currentPerson.getEmail().compareTo(email) == 0 && currentPerson.getPassword().compareTo(password) == 0)
			{
				person = currentPerson;
			}
		}
		System.err.println("PERSONA OBTENIDA: " + person);
		return person;
	}
	
	
	@PostMapping("/registro")
	public void registroPersona(@RequestParam (name = "name") String name, @RequestParam (name = "email") String email, @RequestParam (name = "password") String password1, @RequestParam (name = "password1") String password2, @RequestParam (name = "curso") int curso_id) {

		/*REQUISITOS PARA ESTE IF:
		 * 1. El nombre no esté vacio
		 * 2. El email no esté vacio
		 * 3. El primer password y segundo password coincidan
		 * 4. El primer password no esté vacio
		 * */
		if(password1.compareTo(password2) == 0 && !password1.isEmpty() && !name.isEmpty() && !email.isEmpty() && !password1.isEmpty())
		{
			ps.addPerson(name, email, password1, curso_id);
			System.out.println(":) SE HA GUARDADO LA PERSONA EN LA BASE DE DATOS");
		}
		else
		{
			System.err.println(":( ERROR, NO SE HA CUMPLIDO LOS REQUISITOS PARA EL REGISTRO");
		}
	}
	
	
}
