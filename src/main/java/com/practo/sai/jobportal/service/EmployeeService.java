package com.practo.sai.jobportal.service;

import com.practo.sai.jobportal.entities.Role;
import com.practo.sai.jobportal.model.EmployeeModel;
import com.practo.sai.jobportal.model.LoginModel;

public interface EmployeeService {

	public EmployeeModel addEmployee(LoginModel loginModel);

	public EmployeeModel addEmployee(LoginModel loginModel, Role role);

	public EmployeeModel addEmployeeIfNotExist(LoginModel loginModel);
}
