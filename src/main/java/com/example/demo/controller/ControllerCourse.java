package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.converter.CourseConverter;
import com.example.demo.model.CourseModel;
import com.example.demo.service.CourseService;

@Controller
@RequestMapping("/courses")
public class ControllerCourse {
	private static final String COURSES_VIEW="courses";
	private static final String FORM_VIEW="formCourse";
	private static final String STUDENT_VIEW="cStudents";
	
	@Autowired
	@Qualifier("courseService")
	private CourseService courseService;
	@Autowired
	@Qualifier("courseConverter")
	private CourseConverter courseConverter;
	
	@GetMapping("/listCourses")
	public ModelAndView listCourses() {
		ModelAndView mav=new ModelAndView(COURSES_VIEW);
		mav.addObject("courses",courseService.listAllCourses());
		return mav;
	}
	
	@PostMapping("/addCourse")
	public String addCourse(@ModelAttribute("course") CourseModel courseModel, RedirectAttributes flash) {
		if(courseModel.getId()==0) {
			courseService.addCourse(courseModel);
			flash.addFlashAttribute("success","Curso insertado exitosamente");
		}
		else {
			courseService.updateCourse(courseModel);
			flash.addFlashAttribute("success","Curso actualizado exitosamente");
		}
		return "redirect:/courses/listCourses";
	}
	@GetMapping(value={"/formCourse" , "/formCourse/{id}"})
	public String formCourse( @PathVariable(name="id", required=false) Integer id,Model model) {
		if(id==null) {
			model.addAttribute("course",new CourseModel());
		}
		else {
			model.addAttribute("course", courseService.findCourse(id));
		}
		return FORM_VIEW;
	}
	
	@GetMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable("id") int id, RedirectAttributes flash) {
		if(courseService.removeCourse(id)==0) {
			flash.addAttribute("success","Curso eliminado satisfactoriamente");
		}
		else {
			flash.addAttribute("success", "No se ha podido eliminar el curso");
		}
		return "redirect:/courses/listCourses";
	}
	
	@GetMapping("/showStudents/{id}")
	public String showStudents(@PathVariable("id") int id, Model model) {
		CourseModel course=courseService.findCourse(id);
		model.addAttribute("students", courseService.listAllStudents(course));
		return STUDENT_VIEW;
	}
	

}
