package com.practo.sai.jobportal.repo;

import org.springframework.data.repository.CrudRepository;

import com.practo.sai.jobportal.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

}
