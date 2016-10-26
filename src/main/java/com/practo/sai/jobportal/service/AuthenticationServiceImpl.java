package com.practo.sai.jobportal.service;

import java.security.Principal;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.model.EmployeeModel;
import com.practo.sai.jobportal.model.LoginModel;
import com.practo.sai.jobportal.model.LoginResponse;
import com.practo.sai.jobportal.model.SessionParams;
import com.practo.sai.jobportal.utility.Logger;

import inti.ws.spring.exception.client.UnauthorizedException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private static final Logger LOG = Logger.getInstance(AuthenticationServiceImpl.class);

  @Autowired
  private EmployeeService employeeService;

  @Override
  public LoginResponse authenticate(Principal principal, HttpSession session)
      throws UnauthorizedException {
    OAuth2Authentication auth = (OAuth2Authentication) principal;
    LOG.info("Authentication request received");
    if (auth.isAuthenticated()) {
      @SuppressWarnings("unchecked")
      LinkedHashMap<String, String> details =
          (LinkedHashMap<String, String>) auth.getUserAuthentication().getDetails();

      String domain = details.get(Constants.KEY_DOMAIN);
      if (!Constants.ACCEPTED_DOMAIN.equalsIgnoreCase(domain))
        throw new UnauthorizedException("Unauthorized user");
      String email = details.get(Constants.KEY_EMAIL);
      String name = details.get(Constants.KEY_NAME);

      LoginModel loginModel = new LoginModel();
      loginModel.setEmailId(email);
      loginModel.setName(name);

      EmployeeModel employee = employeeService.addEmployeeIfNotExist(loginModel);

      session.setAttribute(Constants.SESSION_KEY_EMAIL, email);
      session.setAttribute(Constants.SESSION_KEY_EID, employee.getEId());

      LoginResponse response = new LoginResponse();
      response.setEmailId(email);
      response.setRoleModel(employee.getRole());
      response.setName(name);
      response.setPicUrl(details.get(Constants.KEY_PIC));
      LOG.info("Login processed succesfully");
      return response;
    } else {
      throw new UnauthorizedException("Login failed. Please try again");
    }
  }

  @Override
  public SessionParams validateSession(HttpSession session) throws UnauthorizedException {
    SessionParams sessionParams;
    if (session == null)
      throw new UnauthorizedException("Please login to continue");
    String emailId = (String) session.getAttribute(Constants.SESSION_KEY_EMAIL);
    // String role = (String)
    // session.getAttribute(Constants.SESSION_KEY_ROLE);
    Integer eId = (Integer) session.getAttribute(Constants.SESSION_KEY_EID);
    LOG.debug(eId + ",,," + emailId);
    if (emailId != null && !emailId.equals("") && eId != null) {
      sessionParams = new SessionParams();
      sessionParams.setEmailId(emailId);
      // sessionParams.setRole(role);
      sessionParams.seteId(eId);
      LOG.debug("Session validated for - " + eId);
    } else {
      LOG.error("No session");
      throw new UnauthorizedException("Please login to continue");
    }
    return sessionParams;
  }

}
