package com.iesports.dao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iesports.dao.repository.CourseRepository;
import com.iesports.dao.service.ICourseService;
import com.iesports.model.Course;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseRepository cr;

	@Override
	public List<Course> getCourses() {
		return cr.findAll();
	}

	@Override
	public Course getCourse(Long idCourse) {
		return cr.findById(idCourse).orElse(null);
	}

	@Override
	public Course saveCourse(Course course) {
		return cr.save(course);
	}

	@Override
	public Course updateCourse(Course course) {
		return cr.save(course);
	}

	@Override
	public void deleteCourse(Course course) {
		cr.delete(course);
	}
	
	
//	//Método que se encarga de recoger todos los cursos posibles
//	public ArrayList<Course> getCursos(){
//		return (ArrayList<Course>)cr.findAll();
//	}
	
	
	
}
