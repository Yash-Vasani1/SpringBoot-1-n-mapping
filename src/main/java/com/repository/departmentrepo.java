package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Department;

@Repository
public interface departmentrepo extends CrudRepository<Department, Integer> {
	List<Department> findAllByName(String name);
	Department findByName(String name);
}