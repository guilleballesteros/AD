package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(length=100)
	private String name;
	private String firstname;
	private String foto;
	private String sex;
	private float grades;
	@NotNull
	@Min(18)
	private int age;
	
	@ManyToOne
	@JoinColumn(name="courseId")
	private Course course;
	


	public Student(int id, String name,String foto, String firstName, String sex, float grades, @NotNull @Min(18) int age, Course course) {
		this.id = id;
		this.name = name;
		this.firstname = firstName;
		this.sex = sex;
		this.grades = grades;
		this.foto=foto;
		this.age = age;
		this.course = course;
	}



	public Student() {}
	
	
	
	
	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}




	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public float getGrades() {
		return grades;
	}



	public void setGrades(float grades) {
		this.grades = grades;
	}
	
	
	
}