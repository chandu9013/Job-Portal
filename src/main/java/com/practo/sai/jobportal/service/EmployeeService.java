package com.practo.sai.jobportal.service;

import com.practo.sai.jobportal.entities.Role;
import com.practo.sai.jobportal.model.EmployeeModel;

public interface EmployeeService {

	public EmployeeModel addEmployee(String emailId);

	public EmployeeModel addEmployee(String emailId, Role role);
}
