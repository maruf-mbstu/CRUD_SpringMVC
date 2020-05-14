package com.maruf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.maruf.beans.Employee;
import com.maruf.dao.EmpDao;

@Controller
public class EmpController {
	
	@Autowired
	EmpDao dao;
	
	@RequestMapping("/empform")
	public String showform(Model m)
	{
		m.addAttribute("order", new Employee()); 
		return "empform"; 
		//if we set addAtrribute 'command' instead of 'order' or anything           
		// then we wont have to specify modelAttribute in JSP; Internally it will put 'command'
		
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@ModelAttribute("wecansetanyname") Employee emp)
	{
		dao.save(emp);
		return "redirect:/viewemp";
	}
	
	@RequestMapping("/viewemp")
	public String viewemp(Model m)
	{
		List<Employee> list = dao.getEmployees();	
		m.addAttribute("list",list);
		return "viewemp";
	}
	
	@RequestMapping(value="editemp/{id}")
	public String edit(@PathVariable int id, Model m)
	{
		Employee emp = dao.getEmpById(id);
		m.addAttribute("yahoo",emp);
		return "empeditform";
		
	}
	
	@RequestMapping(value="/editsave",method = RequestMethod.POST)
	public String editsave(@ModelAttribute("wecansetanyname") Employee emp)
	{
		dao.update(emp);
		return "redirect:/viewemp";
		
	}
	
	@RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable int id)
	{
		dao.delete(id);
		return "redirect:/viewemp";
		
	}
	
	
}
