package com.practo.sai.jobportal.repo;

import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Role;
import com.practo.sai.jobportal.entities.UserRole;

public interface RoleDao {

	public Role getRoleByName(String role);

	public void addUserRole(UserRole userRole);

	public Role getRolebyEmployee(Employee employee);

}
