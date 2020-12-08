package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import com.example.demo.upload.FileSystemStorageService;
import com.example.demo.upload.StorageService;

@Controller
@RequestMapping("/students")
public class ControllerStudent {
	private static final String COURSES_VIEW="students";
	private static final String FORM_VIEW="formStudent";
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("courseService")
	private CourseService courseSevice;
	
	@Autowired
	private FileSystemStorageService storageService;
	
	@GetMapping("/listStudents")
	public ModelAndView listCourses() {
		ModelAndView mav=new ModelAndView(COURSES_VIEW);
		mav.addObject("students",studentService.listAllStudents());
		return mav;
	}
	
	@PostMapping("/addStudent")
	public String addCourse(@Valid @ModelAttribute("student") StudentModel studentModel, 
			BindingResult bindingResult,RedirectAttributes flash, Model model, @RequestParam("file") MultipartFile file)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("courses", courseSevice.listAllCourses());
			return FORM_VIEW;
		}
		else {
			if(studentModel.getId()==0){
				studentModel = studentService.addStudent(studentModel);
				if(!file.isEmpty()) {
					String imagen = storageService.store(file, studentModel.getId());
					studentModel.setFoto(MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serverFile", imagen).build().toString());
					studentService.updateStudent(studentModel);
				}
				flash.addFlashAttribute("success", "Estudiante insertado correctamente");
			}
			else {
				if(!file.isEmpty()) {
					if(studentModel.getFoto()!=null) {
						storageService.delete(studentModel.getFoto());
					}
					String imagen = storageService.store(file, studentModel.getId());
					studentModel.setFoto(MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serverFile", imagen).build().toString());
				}
				else {
					StudentModel studentOld = studentService.findStudent(studentModel.getId());
					studentModel.setFoto(studentOld.getFoto());
				}
				studentService.updateStudent(studentModel);
				flash.addFlashAttribute("success", "Estudiante modificado correctamente");
				
			}
			return "redirect:/students/listStudents";
		}
	}
	@GetMapping({"/formStudent", "/formStudent/{id}"})
	public String formCourse(@PathVariable(name="id", required=false) Integer id,Model model) {
		model.addAttribute("courses", courseSevice.listAllCourses());
		if(id==null) {
			model.addAttribute("student",new Student());
		}else {
			model.addAttribute("student", studentService.findStudent(id));
		}
		return FORM_VIEW;
	}
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable("id") int id, RedirectAttributes flash) {
		if(studentService.removeStudent(id)==0) {
			flash.addFlashAttribute("success","Se ha eliminado el estudiante satisfactoriamente");
		}
		else
			flash.addFlashAttribute("error","No se ha podido eliminar al estudiante");
		return "redirect: /students/listStudents";
	}

}
