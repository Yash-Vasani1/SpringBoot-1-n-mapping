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
public class DepartmentController {
	
	@Autowired
	departmentrepo deprepo;
	
	@Autowired
	employeerepo emprepo;
	
	@PostMapping("/addDepartment")
	public Department AddDepartmrnt(@RequestBody Department dep)
	{
		deprepo.save(dep);
		return dep;
	}
	
	@GetMapping("/getAllDepartment")
	public List<Department> GetAllDepartment()
	{
		return (List<Department>) deprepo.findAll();
	}
	
	@GetMapping("/getDepartmentById/{id}")
	public Department GetDepartmentById(@PathVariable("id") Integer id)
	{
		Optional<Department> opt = deprepo.findById(id);
		if(opt.isEmpty())
			return null;
		else
			return opt.get();
	}
	
	@GetMapping("/GetDepartmentByName/{name}")
	public List<Department> GetDepartmentByName(@PathVariable("name") String name)
	{
		return deprepo.findAllByName(name);
	}
//	@GetMapping("/GetDepartmentByName/{name}")
//	public String GetDepartmentByName(@PathVariable("name") String name)
//	{
//		if(deprepo.findAllByName(name).isEmpty())
//			return "Empty";
//		else
//			return "Not Empty";
//	}
	
	@DeleteMapping("/DeleteDepartmentById/{id}")
	public Department DeleteDepartmentById(@PathVariable("id") Integer id)
	{
		Department dep = deprepo.findById(id).get();
		deprepo.deleteById(id);
		return dep;
	}
	@PutMapping("/EditDepartment")
	public Department EditDepartment(@RequestBody Department dep)
	{
//		Department dep = deprepo.findById(id).get();
		deprepo.save(dep);
		return dep;
	}
}