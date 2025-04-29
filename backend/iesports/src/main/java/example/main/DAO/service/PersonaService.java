package example.main.DAO.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.main.DAO.repository.PersonaRepository;
import example.main.modelo.Persona;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository pr;
	
	public ArrayList<Persona> getPersonas(){
		return (ArrayList<Persona>) pr.findAll();
	}
	
	
}
