package example.main.modelo;

public class Alumno {
	
	
	//TODO PREGUNTAR A SILVIA ID_CURSO == CLASE_ID
	//¿Posible herencia de "profesor_tutor" a "Alumno"?

	private int id;
	private String nombre;
	private String email;
	private String password;
	private int clase_id;
	private boolean activo;
	private int profesor_id;
	
	public Alumno(int id, String nombre, String email, String password, int clase_id, boolean activo, int profesor_id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.clase_id = clase_id;
		this.activo = activo;
		this.profesor_id = profesor_id;
	}
	
	public Alumno() {
		super();
		this.id = 0;
		this.nombre = null;
		this.email = null;
		this.password = null;
		this.clase_id = 0;
		this.activo = false;
		this.profesor_id = 0;
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

	public int getClase_id() {
		return clase_id;
	}

	public void setClase_id(int clase_id) {
		this.clase_id = clase_id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getProfesor_id() {
		return profesor_id;
	}

	public void setProfesor_id(int profesor_id) {
		this.profesor_id = profesor_id;
	}
	
	
	
	
	
}
