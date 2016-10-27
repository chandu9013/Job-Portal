package com.practo.sai.jobportal.repo;

import com.practo.sai.jobportal.entities.Employee;

/**
 * Class that handles all database operations on {@link Employee}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
public interface EmployeeDao {

  /**
   * Method that adds a new employee to database
   * 
   * @param employee {@link Employee}
   */
  public int save(Employee employee);

  /**
   * Method that retrieves {@link Employee} for the given employee Id
   * 
   * @param eId Employee Id
   * @return {@link Employee}
   */
  public Employee getEmployee(int eId);

  /**
   * Method that retrieves employee based on email
   * 
   * @param email Email Id of the employee
   * @return {@link Employee}
   */
  public Employee getEmployeeByEmail(String email);

}
