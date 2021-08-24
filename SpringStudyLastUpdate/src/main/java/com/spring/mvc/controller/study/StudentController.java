package com.spring.mvc.controller.study;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.dao.study.StudentDAO;
import com.spring.mvc.model.study.Student;

@Controller
public class StudentController {
	
	@Autowired
	StudentDAO studentDAO;
	
	@RequestMapping(value = "/student/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/student/index"); //view
		mav.addObject("student", new Student()); //model
		return mav;
	}
	
	@RequestMapping(value = "/student/addStudent", method = RequestMethod.POST)
	public ModelAndView addStudent(@ModelAttribute Student student) {
		ModelAndView mav = new ModelAndView();
		
		//DB Insert
		studentDAO.insert(student);
		
		mav.setViewName("/student/addStudent"); //view
		mav.addObject("student", student); //model
		return mav;
	}
	
	@RequestMapping(value = "/student/listStudent", method = RequestMethod.GET)
	public ModelAndView listStudent() {
		List<Student> students = studentDAO.select();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/student/listStudent");
		mav.addObject("students", students);
		return mav;
	}
	
}
