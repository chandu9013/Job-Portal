package com.practo.sai.jobportal.controller;

import java.security.Principal;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.model.EmployeeModel;
import com.practo.sai.jobportal.model.LoginModel;
import com.practo.sai.jobportal.model.LoginResponse;
import com.practo.sai.jobportal.service.EmployeeService;
import com.practo.sai.jobportal.utility.Logger;

import inti.ws.spring.exception.client.ForbiddenException;
import inti.ws.spring.exception.client.UnauthorizedException;

@EnableOAuth2Sso
@RestController
public class AuthController extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = Logger.getInstance(AuthController.class);

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public LoginResponse loginUser(Principal principal, HttpSession session)
			throws UnauthorizedException, ForbiddenException {
		LOG.info("Request received for authentication");
		LOG.debug(principal);
		if (principal == null)
			throw new ForbiddenException("Access forbiden");
		OAuth2Authentication auth = (OAuth2Authentication) principal;
		LOG.info("Authentication request received");
		if (auth.isAuthenticated()) {
			LinkedHashMap<String, String> details = (LinkedHashMap<String, String>) auth.getUserAuthentication()
					.getDetails();

			String domain = details.get(Constants.KEY_DOMAIN);
			if (!"practo.com".equalsIgnoreCase(domain))
				throw new UnauthorizedException("Unauthorized user");
			String email = details.get(Constants.KEY_EMAIL);
			String name = details.get(Constants.KEY_NAME);

			LoginModel loginModel = new LoginModel();
			loginModel.setEmailId(email);

			EmployeeModel employee = employeeService.addEmployeeIfNotExist(loginModel);

			session.setAttribute(Constants.SESSION_KEY_EMAIL, email);
			session.setAttribute(Constants.SESSION_KEY_EID, employee.getEId());

			LoginResponse response = new LoginResponse();
			response.setEmailId(email);
			response.setRoleModel(employee.getRole());
			response.setName(name);
			LOG.info("Login processed succesfully");
			return response;
		} else {
			throw new UnauthorizedException("Login failed. Please try again");
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/**", "/login**", "/webjars/**", "/js/**").permitAll()
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf().disable();
	}

}
