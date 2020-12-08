package com.example.demo.model;

public class StudentModel {
	
	
	private int id;
	private String name;
	private String firstname;
	private String sex;
	private String foto;
	private float grades;
	private int age;
	
	public StudentModel(int id, String name, String firstname, String sex, float grades,String foto, int age) {
		super();
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.sex = sex;
		this.grades = grades;
		this.foto = foto;
		this.age = age;
	}
	public StudentModel() {}
	public int getId() {
		return id;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "StudentModel [id=" + id + ", name=" + name + ", firstname=" + firstname + ", sex=" + sex + ", grades="
				+ grades + ", age=" + age + "]";
	}
	
	
	
	
}
