package example.main.control;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.main.DAO.service.PersonaService;
import example.main.modelo.Persona;

@RestController
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	private PersonaService pr;
	
	
	/*Método que devuelve todos los datos de la BBDD*/
	@GetMapping("/todos")
	public ArrayList<Persona> getAllPersonas(){
		return pr.getPersonas();
	}
	
	
	@GetMapping("/login")
	public Persona getPersona(@RequestParam (name = "email") String email, @RequestParam (name = "password") String password) {
		
		ArrayList<Persona> personas = pr.getPersonas();
		Persona persona = null;
		
		for (Persona currentPersona : personas)
		{
			if(currentPersona.getEmail().compareTo(email) == 0 && currentPersona.getPassword().compareTo(password) == 0)
			{
				persona = currentPersona;
			}
		}

		return persona;
	}
	
}
