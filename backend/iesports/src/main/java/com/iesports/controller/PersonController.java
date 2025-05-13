package com.iesports.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.CourseServiceImpl;
import com.iesports.dao.service.impl.PersonServiceImpl;
import com.iesports.dao.service.impl.RoleServiceImpl;
import com.iesports.dto.PersonLoginDTO;
import com.iesports.dto.PersonRegisterDTO;
import com.iesports.model.Person;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonServiceImpl ps;

	@Autowired
	private RoleServiceImpl rs;

	@Autowired
	private CourseServiceImpl cs;

	/* Método que devuelve todos los datos de la BBDD */
	@GetMapping("/getPersons")
	public List<Person> getPersons() {
		return ps.getPersons();
	}

	
//	public Person login(@RequestParam String email, @RequestParam String password) {
//
//		List<Person> persons = ps.getPersons();
//		Person person = null;
//
//		for (Person currentPerson : persons) {
//			if (currentPerson.getEmail().compareTo(email) == 0 && currentPerson.getPassword().compareTo(password) == 0) {
//				person = currentPerson;
//			}
//		}
//		System.err.println("PERSONA OBTENIDA: " + person);
//		return person;
//	}
	@PostMapping("/verify")
	public ResponseEntity<?> login(@Valid @RequestBody PersonLoginDTO person)
	{	
		Person newPerson = ps.getPerson(person.getEmail(), person.getPassword());
		
		//El objeto es nulo en caso de que el password/email no son correctos
		if(newPerson == null)
		{
			System.err.println("No se ha encontrado al usuario rey");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, Correo/Contraseña no válidos");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
	}
	
		
	// La ? en el tipo de respuesta es que puedes devolver cualquier tipo de dato
	// OPCION 1: BODY DE LOS DATOS REQUERIDOS DEL USUARIO
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody PersonRegisterDTO person) {
		
		Map<String, String> errors = new HashMap<>();

		System.out.println("Persona de entrada: " + person.toString());

		if (ps.emailExists(person.getEmail())) {
			System.err.println("El email " + person.getEmail() + " ya existe");
			errors.put("email", "El email " + person.getEmail() + " ya existe");
		}

		if (!person.getPassword1().equals(person.getPassword2())) {
			System.err.println("Las contraseñas no coinciden");
			errors.put("password2", "Las contraseñas no coinciden");
		}
		
		if (errors.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}

		Person newPerson = new Person(null, cs.getCourse(person.getCourseId()), rs.getRole(4L), person.getName(),
				person.getEmail(), person.getPassword1(), 1);
		System.out.println("Persona a registrar: " + person.toString());

		newPerson = ps.savePerson(newPerson);
		System.out.println("Persona registrada: " + newPerson.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
	}

	
	
	// OPCION 2: BODY DEL USUARIO COMPLETO
	@PostMapping("/register2")
	public ResponseEntity<?> register(@Valid @RequestBody Person person) {

		System.out.println("Persona de entrada: " + person.toString());

		if (ps.emailExists(person.getEmail())) {
			System.err.println("El email " + person.getEmail() + " ya existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "El email " + person.getEmail() + " ya existe"));
		}

		// Creamos la persona por defecto con rol de Alumno
		person.setRole(rs.getRole(4L));
		person.setActive(1);
		System.out.println("Persona a registrar: " + person.toString());

		Person newPerson = ps.savePerson(person);
		System.out.println("Persona registrada: " + newPerson.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
	}

	
	
	// OPCION 3: PARAMETROS DE LOS DATOS REQUERIDOS DEL USUARIO
	@PostMapping("/register3")
	public ResponseEntity<?> register(@RequestParam String name, @RequestParam String email, @RequestParam String password1, @RequestParam String password2, @RequestParam Long cursoId) {

		System.out.println("Persona de entrada:" + "\nname: " + name + "\nemail: " + email + "\npassword1: " + password1
				+ "\npassword2: " + password2 + "\ncursoId: " + cursoId);

		if (name == null || name.isBlank() || email == null || email.isBlank() || password1 == null|| password1.isBlank() || password2 == null || password2.isBlank()) {
			System.err.println("Todos los campos son obligatorios");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Todos los campos son obligatorios"));
		}

		if (!password1.equals(password2)) {
			System.err.println("Las contraseñas no coinciden");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Las contraseñas no coinciden"));
		}

		if (ps.emailExists(email)) {
			System.err.println("El email " + email + " ya existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "El email " + email + " ya existe"));
		}

		Person newPerson = new Person(null, cs.getCourse(cursoId), rs.getRole(4L), name, email, password1, 1);
		System.out.println("Persona a registrar: " + newPerson.toString());

		newPerson = ps.savePerson(newPerson);
		System.out.println("Persona registrada: " + newPerson.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);

	}

	/*@PostMapping("/registro")
	public void registroPersona(@RequestParam (name = "name") String name, @RequestParam (name = "email") String email, @RequestParam (name = "password") String password1, @RequestParam (name = "password1") String password2, @RequestParam (name = "curso") int curso_id) {
		if(password1.compareTo(password2) == 0 && !password1.isEmpty() && !name.isEmpty() && !email.isEmpty() && !password1.isEmpty())
		{
			ps.addPerson(name, email, password1, curso_id);
			System.out.println(":) SE HA GUARDADO LA PERSONA EN LA BASE DE DATOS");
		}
		else
		{
			System.err.println(":( ERROR, NO SE HA CUMPLIDO LOS REQUISITOS PARA EL REGISTRO");
		}
	}*/

//	@PostMapping("/registro")
//	public Persona registroPersona(@RequestParam (name = "name") String nombre, @RequestParam (name = "email") String email, @RequestParam (name = "password") String password1, @RequestParam (name = "password") String password2, @RequestParam (name = "curso") int curso_id) {
//		
//		if ( password1.equals(password2) && !password1.isEmpty() && !nombre.isEmpty() && !email.isEmpty() ) {
//			
//			pr.addUser(nombre, email, password1, curso_id );
//		}
//		
//		return null;
//		
//	}

}
