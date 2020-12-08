package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Course;
import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;

public interface CourseService {
	List<CourseModel> listAllCourses();
	CourseModel findCourse(int id);
	Course addCourse(CourseModel courseModel);
	int removeCourse(int id);
	Course updateCourse(CourseModel courseModel);
	public abstract List<StudentModel> listAllStudents(CourseModel courseModel);
}
