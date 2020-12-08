package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.converter.CourseConverter;
import com.example.demo.entity.Course;
import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.CourseService;
@Service("courseService")
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	@Qualifier("courseRepository")
	private CourseRepository courseRepository;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository;

	public List<CourseModel> listAllCourses() {
		
		List<CourseModel> courseModels=new ArrayList<>();
		for(Course course:courseRepository.findAll()) {
			courseModels.add(courseConverter.entity2model(course));
		}
		return courseModels;
	}
	
	@Autowired
	@Qualifier("courseConverter")
	private CourseConverter courseConverter;
	
	public Course addCourse(CourseModel courseModel) {
		Course course=courseConverter.model2entity(courseModel);
		return courseRepository.save(course);
	}

	public int removeCourse(int id) {
		courseRepository.deleteById(id);
		return 0;
	}

	public Course updateCourse(CourseModel courseModel) {
		Course course=courseConverter.model2entity(courseModel);
		return courseRepository.save(course);
	}

	@Override
	public CourseModel findCourse(int id) {
		return courseConverter.entity2model(courseRepository.findById(id).orElse(null));
	}

	@Override
	public List<StudentModel> listAllStudents(CourseModel courseModel) {
		return studentRepository.findByCourse(courseConverter.model2entity(courseModel)).
				stream().map(c-> dozer.map(c, StudentModel.class)).collect(Collectors.toList());
	}

}
