package com.iesports.dao.service;

import java.util.List;

import com.iesports.model.Course;

public interface ICourseService {

	public List<Course> getCourses();
	
	public Course getCourse(Long idCourse);
		
	public Course saveCourse(Course course);
	
	public Course updateCourse(Course course);
	
	public void deleteCourse(Course course);
}
