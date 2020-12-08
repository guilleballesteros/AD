package com.example.demo.converter;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Course;
import com.example.demo.model.CourseModel;

@Component("courseConverter")
public class CourseConverter {
	
	//Entity->Model
	public CourseModel entity2model(Course course) {
		CourseModel courseModel=new CourseModel();
		courseModel.setId(course.getId());
		courseModel.setName(course.getName());
		courseModel.setDescription(course.getDescription());
		courseModel.setPrice(course.getPrice());
		courseModel.setHours(course.getHours());
		return courseModel;
	}
	
	
	//Model->Entity
	public Course model2entity(CourseModel courseModel) {
		
		Course course=new Course();
		course.setId(courseModel.getId());
		course.setName(courseModel.getName());
		course.setDescription(courseModel.getDescription());
		course.setPrice(courseModel.getPrice());
		course.setHours(courseModel.getHours());
		return course;
	}
}
