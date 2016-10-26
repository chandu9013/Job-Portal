package com.practo.sai.jobportal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.practo.sai.jobportal.model.EmployeeModel;
import com.practo.sai.jobportal.model.LoginModel;
import com.practo.sai.jobportal.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class EmployeeServiceTest {

  @Autowired
  EmployeeService employeeService;

  @Test
  public void addEmployeeIfNotExist() {
    LoginModel login = new LoginModel();
    login.setEmailId("sai.chandra@practo.com");
    login.setName("Sai Chandra Sekhar Dandu");

    EmployeeModel employee = employeeService.addEmployeeIfNotExist(login);
    assertEquals((Integer) 12, employee.getEId());
  }
}
