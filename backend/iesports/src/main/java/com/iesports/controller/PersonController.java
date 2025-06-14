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
import com.iesports.dto.ChangeRoleDTO;
import com.iesports.dto.ContactFormRequestDTO;
import com.iesports.dto.ForgotPasswordRequestDTO;
import com.iesports.dto.PersonLoginDTO;
import com.iesports.dto.PersonRegisterDTO;
import com.iesports.model.Person;
import com.iesports.model.Role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/person")
@Tag(name = "Gestión de personas", description = "Endpoints para la gestión de personas")
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/* Método que devuelve todos los datos de la BBDD */
	@Operation(summary = "Obtener la lista de todas las personas")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Lista de personas obtenida correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            array = @ArraySchema(schema = @Schema(implementation = Person.class))
	        )
	    )
	})
	@GetMapping("/getPersons")
	public List<Person> getPersons() {
		return ps.getPersons();
	}
	
	@Operation(summary = "Obtener una persona por su ID")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Persona encontrada correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class)
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "ID proporcionado no válido",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(example = "{\"person\": \"Las credenciales introducidas no son válidas\"}")
	        )
	    ),
	    @ApiResponse(
	        responseCode = "404",
	        description = "Persona no encontrada con el ID proporcionado",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(example = "{\"person\": \"No se encontró una persona con el ID proporcionado\"}")
	        )
	    )
	})
	@GetMapping("/getPersonById")
	public ResponseEntity<?> getPersonById(@RequestParam Long id) {
	    if (id == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Map.of("person", "Las credenciales introducidas no son válidas"));
	    }

	    Person person = ps.getPersonById(id);
	    if (person == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(Map.of("person", "No se encontró una persona con el ID proporcionado"));
	    }

	    return ResponseEntity.ok(person);
	}

	
	@Operation(summary = "Obtener la lista de personas con el rol de alumno")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Lista de personas con rol alumno obtenida correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            array = @ArraySchema(schema = @Schema(implementation = Person.class))
	        )
	    )
	})
	@GetMapping("/getPersonsRoleStudent")
	public List<Person> getPersonsRoleStudent() {
	    Long studentRoleId = rs.getRoles().stream()
	        .filter(role -> role.getName().equalsIgnoreCase("alumno"))
	        .map(Role::getId)
	        .findFirst()
	        .orElse(null);

	    if (studentRoleId == null) {
	        return List.of();
	    }

	    return ps.getPersons().stream()
	        .filter(person -> person.getRole() != null && person.getRole().getId().equals(studentRoleId))
	        .toList();
	}
	
	@Operation(summary = "Iniciar sesión con email y contraseña")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Inicio de sesión exitoso, devuelve la información del usuario",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "{\"id\":1,\"name\":\"Juan\",\"email\":\"juan@mail.com\"}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Credenciales inválidas (email o contraseña incorrectos)",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"error\":\"Las credenciales introducidas no son válidas\"}"
	            )
	        )
	    )
	})
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
	

	// La ? en el tipo de respuesta es que puedes devolver cualquier tipo de dato
	// OPCION 1: BODY DE LOS DATOS REQUERIDOS DEL USUARIO
	@Operation(summary = "Registrar una nueva persona")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "201",
	        description = "Persona registrada correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "{\"id\":10,\"name\":\"Laura\",\"email\":\"laura@mail.com\",\"role\":{\"id\":4,\"name\":\"estudiante\"}}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Error en la validación de datos de entrada",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"email\":\"El email laura@mail.com ya existe\",\"password2\":\"Las contraseñas no coinciden\"}"
	            )
	        )
	    )
	})
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

	@Operation(summary = "Solicitar contraseña temporal para recuperación de cuenta")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Contraseña temporal enviada correctamente, devuelve la persona actualizada",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "{\"id\":5,\"name\":\"Luis\",\"email\":\"luis@mail.com\",\"tempPassword\":1}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "El correo no pertenece a ningún usuario registrado",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"emailForgotPassword\":\"No existe un usuario registrado con el correo luis@mail.com\"}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "500",
	        description = "Error interno al enviar el correo con la contraseña temporal",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"correo\":\"No se pudo enviar el correo con la contraseña temporal. Por favor intentelo de nuevo\"}"
	            )
	        )
	    )
	})
	@PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDTO emailDTO){
		
		Person person = ps.getPersonByEmail(emailDTO.getEmailForgotPassword());
		
		if(person == null) {
			System.err.println("El email " + emailDTO.getEmailForgotPassword() + " no pertenece a un usuario registrado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("emailForgotPassword", "No existe un usuario registrado con el correo " + emailDTO.getEmailForgotPassword()));
		}

		String passwordTemp = gtps.generateTemporaryPassword();
		String passwordTempEncripted = passwordEncoder.encode(passwordTemp);
		
		person.setTempPassword(1);
		person.setPassword(passwordTempEncripted);
		
		try {
			ms.sendMailForgotPassword(emailDTO.getEmailForgotPassword(), passwordTemp, person.getName());
			person = ps.updatePerson(person);
			
			System.out.println("Contraseña temporal para el usuario " + person.getId() + " es: " + passwordTemp);

			return ResponseEntity.status(HttpStatus.OK).body(person);
		} catch (Exception e) {
			 e.printStackTrace();
			 
			System.out.println("No se pudo enviar la contraseña temporal al correo " + emailDTO.getEmailForgotPassword() + " del usuario " + person.getName());

		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("correo", "No se pudo enviar el correo con la contraseña temporal. Por favor intentelo de nuevo"));
		}
		
	}
	
	@Operation(summary = "Cambiar la contraseña temporal por una nueva contraseña definitiva")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Contraseña cambiada correctamente, devuelve la persona actualizada",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "{\"id\":5,\"name\":\"Luis\",\"email\":\"luis@mail.com\",\"tempPassword\":0}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Error en la validación de datos de entrada",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"personId\":\"Usuario no encontrado\", \"password2TempPaswword\":\"Las contraseñas no coinciden\"}"
	            )
	        )
	    )
	})
	@PostMapping("/changeTempPassword")
	public ResponseEntity<?> changeTempPassword(@Valid @RequestBody ChangeForgottenPasswordDTO changePassDTO){
		
		Map<String, String> errors = new HashMap<>();
		
		Person person = ps.getPersonById(changePassDTO.getPersonId());
		
		if(person == null) {
			System.err.println("Usuario no encontrado");
			errors.put("personId", "Usuario no encontrado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		if (!changePassDTO.getPassword1TempPaswword().equals(changePassDTO.getPassword2TempPaswword())) {
			System.err.println("Las contraseñas no coinciden");
			errors.put("password2TempPaswword", "Las contraseñas no coinciden");
		}
		
		if (errors.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		String passwordEncripted = passwordEncoder.encode(changePassDTO.getPassword1TempPaswword());
		
		person.setPassword(passwordEncripted);
		person.setTempPassword(0);
		
		person = ps.updatePerson(person);
		
		return ResponseEntity.status(HttpStatus.OK).body(person);
	}
	
	
	@Operation(summary = "Cambiar el nombre y correo electrónico de un usuario")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Datos actualizados correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "{\"id\":3,\"name\":\"Ana\",\"email\":\"ana@mail.com\"}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Errores en la solicitud, como usuario no encontrado, email duplicado o sin cambios",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"personId\":\"Usuario no encontrado\",\"email\":\"El correo electronico ya pertenece a un usuario\"}"
	            )
	        )
	    )
	})
	@PostMapping("/changeNameAndEmail")
	public ResponseEntity<?> changeNameAndEmail(@Valid @RequestBody ChangeNameAndEmailDTO changeNameEmailDTO){
		
		Map<String, String> errors = new HashMap<>();
		
		Person person = ps.getPersonById(changeNameEmailDTO.getPersonId());
		
		if(person == null) {
			System.err.println("Usuario no encontrado");
			errors.put("personId", "Usuario no encontrado");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		if(person.getEmail().equals(changeNameEmailDTO.getEmail())&& person.getName().equals(changeNameEmailDTO.getName())) {
			System.err.println("No se ha realizado ningun cambio en el usuario");
			errors.put("name", "No se ha realizado ningun cambio en el usuario");
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
	
	@Operation(summary = "Cambiar la contraseña de un usuario")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Contraseña cambiada correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "{\"id\":3,\"name\":\"Ana\",\"email\":\"ana@mail.com\"}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Errores en la solicitud, como usuario no encontrado, credenciales incorrectas o contraseñas no coinciden",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"personId\":\"Usuario no encontrado\",\"currentPassword\":\"Credenciales no validas\",\"password2ChangePassword\":\"Las contraseñas no coinciden\"}"
	            )
	        )
	    )
	})
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
			errors.put("currentPassword", "Credenciales no validas");
		}
		
		if (!changePassDTO.getPassword1ChangePassword().equals(changePassDTO.getPassword2ChangePassword())) {
			System.err.println("Las contraseñas no coinciden");
			errors.put("password2ChangePassword", "Las contraseñas no coinciden");
		}
		
		if (errors.size() > 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		String passwordEncripted = passwordEncoder.encode(changePassDTO.getPassword1ChangePassword());
		person.setPassword(passwordEncripted);
		person = ps.updatePerson(person);
		return ResponseEntity.status(HttpStatus.OK).body(person);
	}
	
	@Operation(summary = "Cambiar el rol de un usuario")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Rol cambiado correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "{\"id\":3,\"name\":\"Ana\",\"role\":{\"id\":2,\"name\":\"admin\"}}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Errores en la solicitud, como usuario o rol no encontrados",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"personId\":\"Usuario no encontrado\",\"roleId\":\"Rol no encontrado\"}"
	            )
	        )
	    )
	})
	@PostMapping("/changeRole")
	public ResponseEntity<?> changeUserRole(@Valid @RequestBody ChangeRoleDTO dto) {
	    Map<String, String> errors = new HashMap<>();

	    Person person = ps.getPersonById(dto.getPersonId());
	    if (person == null) {
	        errors.put("personId", "Usuario no encontrado");
	    }

	    Role role = rs.getRole(dto.getRoleId());
	    if (role == null) {
	        errors.put("roleId", "Rol no encontrado");
	    }

	    if (!errors.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	    }

	    person.setRole(role);
	    ps.updatePerson(person);

	    return ResponseEntity.ok(person);
	}
	
	@Operation(summary = "Enviar una consulta a través del formulario de contacto")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "200",
	        description = "Consulta enviada correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(example = "{\"mensaje\": \"Consulta enviada correctamente. Nos pondremos en contacto contigo en breve.\"}")
	        )
	    ),
	    @ApiResponse(
	        responseCode = "500",
	        description = "Error al enviar la consulta",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(example = "{\"email\": \"No se pudo enviar tu consulta. Por favor, intenta de nuevo más tarde.\"}")
	        )
	    )
	})
	@PostMapping("/contactForm")
	public ResponseEntity<?> contactForm(@Valid @RequestBody ContactFormRequestDTO contactDTO) {
	    try {
	        ms.sendUserQuestion(contactDTO.getEmail(), contactDTO.getMessage());

	        return ResponseEntity.ok(Map.of("mensaje", "Consulta enviada correctamente. Nos pondremos en contacto contigo en breve."));
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("email", "No se pudo enviar tu consulta. Por favor, intenta de nuevo más tarde."));
	    }
	}

	
	//SIRVE DE RELLENO PARA LA DOCUMENTACIÓN

	// OPCION 2: BODY DEL USUARIO COMPLETO
	@Operation(summary = "Registrar una nueva persona (opción con objeto Person completo)")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "201",
	        description = "Persona registrada correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "{\"id\":15,\"name\":\"Carlos\",\"email\":\"carlos@mail.com\",\"role\":{\"id\":4,\"name\":\"estudiante\"}}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Email ya existente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"error\":\"El email carlos@mail.com ya existe\"}"
	            )
	        )
	    )
	})
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
	@Operation(summary = "Registrar una nueva persona (opción con parámetros)")
	@ApiResponses({
	    @ApiResponse(
	        responseCode = "201",
	        description = "Persona registrada correctamente",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(implementation = Person.class),
	            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
	                value = "{\"id\":16,\"name\":\"Sara\",\"email\":\"sara@mail.com\",\"role\":{\"id\":4,\"name\":\"estudiante\"}}"
	            )
	        )
	    ),
	    @ApiResponse(
	        responseCode = "400",
	        description = "Error en los datos recibidos (campos obligatorios, contraseñas, email duplicado)",
	        content = @Content(
	            mediaType = "application/json",
	            schema = @Schema(
	                example = "{\"error\":\"Todos los campos son obligatorios\"}"
	            )
	        )
	    )
	})
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
