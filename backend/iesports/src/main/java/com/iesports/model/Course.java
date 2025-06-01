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
	private String initials;
	
	//TODO PREGUNTAR A SILVIA POR QUÉ AÑO
	
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
