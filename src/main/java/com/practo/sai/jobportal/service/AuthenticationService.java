package com.practo.sai.jobportal.service;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import com.practo.sai.jobportal.model.LoginResponse;
import com.practo.sai.jobportal.model.SessionParams;

import inti.ws.spring.exception.client.UnauthorizedException;

public interface AuthenticationService {

	public LoginResponse authenticate(Principal principal, HttpSession session) throws UnauthorizedException;

	SessionParams validateSession(HttpSession session) throws UnauthorizedException;

}
