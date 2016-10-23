package com.practo.sai.jobportal.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.practo.sai.jobportal.model.LoginResponse;
import com.practo.sai.jobportal.service.AuthenticationService;
import com.practo.sai.jobportal.utility.Logger;

import inti.ws.spring.exception.client.ForbiddenException;
import inti.ws.spring.exception.client.UnauthorizedException;

@EnableOAuth2Sso
@RestController
public class AuthController extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = Logger.getInstance(AuthController.class);

	@Autowired
	AuthenticationService authenticationService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public LoginResponse loginUser(Principal principal, HttpSession session)
			throws UnauthorizedException, ForbiddenException {
		LOG.info("Request received for authentication");
		LOG.debug(principal);
		if (principal == null)
			throw new ForbiddenException("Access forbiden");
		return authenticationService.authenticate(principal, session);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/**", "/login**", "/webjars/**", "/js/**").permitAll()
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf().disable();
	}

}
