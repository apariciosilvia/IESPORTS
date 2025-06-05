package com.iesports.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "courses")
@Schema(description = "Entidad que representa una curso")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del curso", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre completo del curso", example = "Desarrollo de Aplicaciones Web", required = true)
    private String name;

    @Schema(description = "Año académico del curso", example = "1", required = true)
    private int year;

    @Schema(description = "Iniciales o acrónimo del curso", example = "DAW", required = true)
    private String initials;
	
	public Course() {
		
	}

	public Course(Long id, String name, int year, String initials) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.initials = initials;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", year=" + year + ", initials=" + initials + "]";
	}
	
	
	
	
}
