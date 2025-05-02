package com.iesports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iesports.dao.service.impl.CourseServiceImpl;
import com.iesports.model.Course;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseServiceImpl cs;

	@GetMapping("/getCourses")
	public ResponseEntity<List<Course>> getCourses() {
		
		List<Course> courses = cs.getCourses();
		
		if (courses.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courses);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(courses);
	}

}
