package com.practo.sai.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Role;
import com.practo.sai.jobportal.entities.UserRole;
import com.practo.sai.jobportal.model.EmployeeModel;
import com.practo.sai.jobportal.model.LoginModel;
import com.practo.sai.jobportal.model.RoleModel;
import com.practo.sai.jobportal.repo.EmployeeDao;
import com.practo.sai.jobportal.repo.RoleDao;
import com.practo.sai.jobportal.utility.Logger;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOG = Logger.getInstance(EmployeeServiceImpl.class);

	@Autowired
	RoleDao roleDao;

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public EmployeeModel addEmployee(LoginModel loginModel, Role role) {
		Employee employee = new Employee();
		employee.setEmailId(loginModel.getEmailId());
		// Add Employee to DB
		employeeDao.save(employee);
		// Add Role to UserRole

		UserRole userRole = new UserRole();
		userRole.setEmployee(employee);
		userRole.setRole(role);
		roleDao.addUserRole(userRole);

		RoleModel roleModel = new RoleModel();
		roleModel.setRId(role.getRId());
		roleModel.setRoleName(role.getRoleName());
		EmployeeModel employeeModel = new EmployeeModel(employee.getEId(), employee.getEmailId(), roleModel);
		return employeeModel;
	}

	@Override
	public EmployeeModel addEmployee(LoginModel loginModel) {

		// Add Employee to DB
		Role role = roleDao.getRoleByName(Constants.ROLE_EMPLOYEE);

		EmployeeModel employeeModel = addEmployee(loginModel, role);

		return employeeModel;
	}

	@Override
	public EmployeeModel addEmployeeIfNotExist(LoginModel loginModel) {
		Employee employee = employeeDao.getEmployeeByEmail(loginModel.getEmailId());
		if (employee == null) {
			return addEmployee(loginModel);
		} else {
			LOG.debug("Employee already exists");
			Role role = roleDao.getRolebyEmployee(employee);
			RoleModel roleModel = new RoleModel();
			roleModel.setRId(role.getRId());
			roleModel.setRoleName(role.getRoleName());
			EmployeeModel employeeModel = new EmployeeModel(employee.getEId(), employee.getEmailId(), roleModel);
			return employeeModel;
		}
	}

}
