package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
@Service("studentService")
public class StudentServiceImpl implements StudentService{
	
	
	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	public Student addStudent(StudentModel student) {
		// TODO Auto-generated method stub
		return studentRepository.save(transform(student));
	}

	@Override
	public List<StudentModel> listAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll().stream().map(c->transform(c)).collect(Collectors.toList());
	}

	

	@Override
	public int removeStudent(int id) {
		studentRepository.deleteById(id);
		return 0;
	}

	@Override
	public Student updateStudent(StudentModel student) {
		return studentRepository.save(transform(student));
	}

	@Override
	public Student transform(StudentModel studentModel) {
		return dozer.map(studentModel, Student.class);
	}

	@Override
	public StudentModel transform(Student student) {
		return dozer.map(student, StudentModel.class);
	}

	@Override
	public StudentModel findStudent(int id) {
		return transform(studentRepository.findById(id).orElse(null));
	}

	

}
