package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Department;
import com.entity.Employee_dep;
import com.repository.departmentrepo;
import com.repository.employeerepo;

@RestController
public class EmployeeController {
	@Autowired
	employeerepo emprepo;
	
	@Autowired
	departmentrepo deprepo;
	
	@PostMapping("/addEmployee")
	public Employee_dep AddEmployee(@RequestBody Employee_dep emp)
	{
		String name = emp.getDepartment().getName();
		Department dep = deprepo.findByName(name);
		if(dep == null)
		{
			deprepo.save(emp.getDepartment());
			emprepo.save(emp);
			return emp;
		}
		else
		{
			emp.setDepartment(dep);
			emprepo.save(emp);
			return emp;
		}
	}
	
	@GetMapping("/getAllEmployee")
	public List<Employee_dep> GetAllEmployee()
	{
		return emprepo.findAll();
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public Employee_dep GetEmployeeById(@PathVariable("id") Integer id)
	{
		Optional<Employee_dep> emp = emprepo.findById(id);
		if(emp.isEmpty())
			return null;
		else
			return emp.get();
	}
	
	@GetMapping("/getEmployeeByName/{name}")
	public List<Employee_dep> GetEmployeeByName(@PathVariable("name") String name)
	{
		return emprepo.findAllByName(name);
	}
	
	@DeleteMapping("/deleteEmployeeById/{id}")
	public Employee_dep DeleteEmployeeById(@PathVariable("id") Integer id)
	{
		Employee_dep emp = emprepo.findById(id).get();
		emprepo.deleteById(id);
		return emp;
	}
	
	@PutMapping("/updateEmployee")
	public Employee_dep UpdateEmployee(@RequestBody Employee_dep emp)
	{
		emprepo.save(emp);
		return emp;
	}
}