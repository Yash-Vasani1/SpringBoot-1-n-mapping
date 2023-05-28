package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Employee_dep;

@Repository
public interface employeerepo extends CrudRepository<Employee_dep, Integer> {
	List<Employee_dep> findAll();

	List<Employee_dep> findAllByName(String name);
}
