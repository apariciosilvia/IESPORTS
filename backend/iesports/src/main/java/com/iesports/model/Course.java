package com.iesports.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "courses")
public class Course {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int year;
	
	//TODO PREGUNTAR A SILVIA POR QUÉ AÑO
	
	public Course() {
		
	}
	
	public Course(Long id, String nombre){
		this.id = id;
		this.name = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", year=" + year + "]";
	}
	
	
}
