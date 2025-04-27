package example.main.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "Persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int curso_id;
	private String nombre;
	private String email;
	private String password;
	private int activo;
	private int rol_id;
	
	public Persona(int id, int curso_id, String nombre, String email, String password, int activo, int rol_id) {
		super();
		this.id = id;
		this.curso_id = curso_id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.activo = activo;
		this.rol_id = rol_id;
	}
	
	

	public Persona() {
		super();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurso_id() {
		return curso_id;
	}

	public void setCurso_id(int curso_id) {
		this.curso_id = curso_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public int getRol_id() {
		return rol_id;
	}

	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}
	
}
