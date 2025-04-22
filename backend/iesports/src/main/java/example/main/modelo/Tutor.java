package example.main.modelo;

public class Tutor {

	private int id;
	private String nombre;
	private String email;
	private String password; //Hacer encriptación de información preferiblemente
	private boolean activo;
	private int id_curso;
	
	
	public Tutor(int id, String nombre, String email, String password, boolean activo, int id_curso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.activo = activo;
		this.id_curso = id_curso;
	}
	
	public Tutor() {
		super();
		this.id = 0;
		this.nombre = null;
		this.email = null;
		this.password = null;
		this.activo = false;
		this.id_curso = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}
	
	
	
	
	
	
	
	
	
}
