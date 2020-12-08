package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;

public interface StudentService {
	List<StudentModel> listAllStudents();
	Student addStudent(StudentModel student);
	int removeStudent(int id);
	Student updateStudent(StudentModel student);
	Student transform(StudentModel studentModel);
	StudentModel transform(Student student);
	StudentModel findStudent(int id);
}
