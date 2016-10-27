package com.practo.sai.jobportal.repo;

import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Role;
import com.practo.sai.jobportal.entities.UserRole;

/**
 * Dao that deals with all database operations on {@link Role} and {@link UserRole}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
public interface RoleDao {

  /**
   * Method that retrieves Role by role name
   * 
   * @param role {@link Role}
   * @return {@link Role} with role Id
   */
  public Role getRoleByName(String role);

  /**
   * Method that adds a new role for an employee
   * 
   * @param userRole {@link UserRole}
   */
  public void addUserRole(UserRole userRole);

  /**
   * Method to retrieve role of an employee
   * 
   * @param employee {@link Employee}
   * @return {@link Role}
   */
  public Role getRolebyEmployee(Employee employee);

}
