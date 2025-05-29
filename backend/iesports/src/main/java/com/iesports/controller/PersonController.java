package com.iesports.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.GenerateTempPasswordService;
import com.iesports.dao.service.MailService;
import com.iesports.dao.service.impl.CourseServiceImpl;
import com.iesports.dao.service.impl.PersonServiceImpl;
import com.iesports.dao.service.impl.RoleServiceImpl;
import com.iesports.dto.ChangeForgottenPasswordDTO;
import com.iesports.dto.ChangeNameAndEmailDTO;
import com.iesports.dto.ChangePasswordDTO;
import com.iesports.dto.ForgotPasswordRequestDTO;
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
	
	@Autowired
	private MailService ms;
	
	@Autowired
	private GenerateTempPasswordService gtps;

	/* Método que devuelve todos los datos de la BBDD */
	@GetMapping("/getPersons")
	public List<Person> getPersons() {
		return ps.getPersons();
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody PersonLoginDTO person)
	{	
		Person foundPerson = ps.getPersonByEmail(person.getEmail());
		
		//El objeto es nulo en caso de que el password/email no son correctos
		if(foundPerson == null)
		{
			System.err.println("No se ha encontrado al usuario");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error","Las credenciales introducidas no son válidas"));
		}
		
		if (!passwordEncoder.matches(person.getPassword(), foundPerson.getPassword())) {
			System.err.println("Las contraseñas no coinciden");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error","Las credenciales introducidas no son válidas"));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(foundPerson);
	}
	
		
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

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

		String passwordEncripted = passwordEncoder.encode(person.getPassword1());

		Person newPerson = new Person(null, cs.getCourse(person.getCourseId()), rs.getRole(4L), person.getName(), person.getEmail(), passwordEncripted, 1, 0);
		System.out.println("Persona a registrar: " + person.toString());

		newPerson = ps.savePerson(newPerson);
		
		System.out.println("Persona registrada: " + newPerson.toString());
		
		try {
		    ms.sendWelcomeEmail(person.getEmail(), person.getName());

		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);

		}

	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDTO emailDTO){
		
		Person person = ps.getPersonByEmail(emailDTO.getEmail());
		
		if(person == null) {
			System.err.println("El email " + emailDTO.getEmail() + " no pertenece a un usuario registrado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("email", "No existe un usuario registrado con el correo " + emailDTO.getEmail()));
		}

		String passwordTemp = gtps.generateTemporaryPassword();
		String passwordTempEncripted = passwordEncoder.encode(passwordTemp);
		
		person.setTempPassword(1);
		person.setPassword(passwordTempEncripted);
		
		try {
			ms.sendMailForgotPassword(emailDTO.getEmail(), passwordTemp, person.getName());
			person = ps.updatePerson(person);
			
			System.out.println("Contraseña temporal para el usuario " + person.getId() + " es: " + passwordTemp);

			return ResponseEntity.status(HttpStatus.OK).body(person);
		} catch (Exception e) {
			 e.printStackTrace();
			 
			System.out.println("No se pudo enviar la contraseña temporal al correo "+emailDTO.getEmail()+" del usuario "+person.getName());

		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("correo", "No se pudo enviar el correo con la contraseña temporal. Por favor intentelo de nuevo"));
		}
		
	}
	
	@PostMapping("/changeTempPassword")
	public ResponseEntity<?> changeTempPassword(@Valid @RequestBody ChangeForgottenPasswordDTO changePassDTO){
		
		Map<String, String> errors = new HashMap<>();
		
		Person person = ps.getPersonById(changePassDTO.getPersonId());
		
		if(person == null) {
			System.err.println("Usuario no encontrado");
			errors.put("personId", "Usuario no encontrado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		if (!changePassDTO.getPassword1().equals(changePassDTO.getPassword2())) {
			System.err.println("Las contraseñas no coinciden");
			errors.put("password2", "Las contraseñas no coinciden");
		}
		
		if (errors.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		String passwordEncripted = passwordEncoder.encode(changePassDTO.getPassword1());
		
		person.setPassword(passwordEncripted);
		person.setTempPassword(0);
		
		person = ps.updatePerson(person);
		
		return ResponseEntity.status(HttpStatus.OK).body(person);
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changePassDTO){
		
		Map<String, String> errors = new HashMap<>();
		
		Person person = ps.getPersonById(changePassDTO.getPersonId());
		
		if(person == null) {
			System.err.println("Usuario no encontrado");
			errors.put("personId", "Usuario no encontrado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		if(!passwordEncoder.matches(changePassDTO.getCurrentPassword(), person.getPassword())) {
			System.err.println("Credenciales no validas");
			errors.put("credenciales", "Credenciales no validas");
		}
		
		if (!changePassDTO.getPassword1().equals(changePassDTO.getPassword2())) {
			System.err.println("Las contraseñas no coinciden");
			errors.put("password2", "Las contraseñas no coinciden");
		}
		
		if (errors.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		String passwordEncripted = passwordEncoder.encode(changePassDTO.getPassword1());
		person.setPassword(passwordEncripted);
		person = ps.updatePerson(person);
		return ResponseEntity.status(HttpStatus.OK).body(person);
	}
	
	@PostMapping("/changeNameAndEmail")
	public ResponseEntity<?> changeNameAndEmail(@Valid @RequestBody ChangeNameAndEmailDTO changeNameEmailDTO){
		
		Map<String, String> errors = new HashMap<>();
		
		Person person = ps.getPersonById(changeNameEmailDTO.getPersonId());
		
		if(person == null) {
			System.err.println("Usuario no encontrado");
			errors.put("personId", "Usuario no encontrado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		if(!person.getEmail().equals(changeNameEmailDTO.getEmail())&& ps.emailExists(changeNameEmailDTO.getEmail())) {
			System.err.println("El correo electronico ya pertenece a un usuario");
			errors.put("email", "El correo electronico ya pertenece a un usuario");
			
		}
		
		if (errors.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		person.setName(changeNameEmailDTO.getName());
		person.setEmail(changeNameEmailDTO.getEmail());
		person = ps.updatePerson(person);
		return ResponseEntity.status(HttpStatus.OK).body(person);
	}	

	//SIRVE DE RELLENO PARA LA DOCUMENTACIÓN

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

		Person newPerson = new Person(null, cs.getCourse(cursoId), rs.getRole(4L), name, email, password1, 1, 0);
		System.out.println("Persona a registrar: " + newPerson.toString());

		newPerson = ps.savePerson(newPerson);
		System.out.println("Persona registrada: " + newPerson.toString());

		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
	}
	
}
