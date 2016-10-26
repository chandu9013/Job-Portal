package com.practo.sai.jobportal.repo;

import com.practo.sai.jobportal.entities.Employee;

public interface EmployeeDao {

  public void save(Employee employee);

  public Employee getEmployee(int eId);

  public Employee getEmployeeByEmail(String email);

}
