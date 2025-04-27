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
	
	//cambio sebas 3
	@Autowired
	private PersonaService pr;
	
	@GetMapping("/todos")
	public ArrayList<Persona> getAllPersonas(){
		return pr.getPersonas();
	}
	
	
	@GetMapping("/selectPersona")
	public Persona getPersona(@RequestParam (name = "nombre") String nombre, @RequestParam (name = "password") String password) {
		
		ArrayList<Persona> personas = pr.getPersonas();
		Persona persona = null;
		
		
		for (Persona currentPersona : personas)
		{
			if(currentPersona.getNombre().compareTo(nombre) == 0 && currentPersona.getPassword().compareTo(password) == 0)
			{
				persona = currentPersona;
			}
		}
		
		return persona;
	}
	
	
}
