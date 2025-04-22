package example.main.modelo;

public class Curso {

	private int id;
	private String nombre;
	//TODO PREGUNTAR A SILVIA POR QUÉ AÑO
	//private int year;
	
	
	public Curso() {
		this.id = 0;
		this.nombre = null;
	}
	
	public Curso(int id, String nombre){
		this.id = id;
		this.nombre = nombre;
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
}
