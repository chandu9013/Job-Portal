package com.practo.sai.jobportal.repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.practo.sai.jobportal.model.Employee;

@Transactional
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

	public Employee findByEmailId(String emailId);

}
