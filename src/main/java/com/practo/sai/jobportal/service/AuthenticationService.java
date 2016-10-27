package com.practo.sai.jobportal.service;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import com.practo.sai.jobportal.model.LoginResponse;
import com.practo.sai.jobportal.model.SessionParams;

import inti.ws.spring.exception.client.UnauthorizedException;

/**
 * Service that provides authentication and session validation for user requests.
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
public interface AuthenticationService {

  /**
   * 
   * @param principal {@link Principal} Object that contains OAuth response from the OAuth provider
   *        (Google).
   * @param session HttpSession object where user's session attributes are set.
   * @return {@link LoginResponse}
   * @throws UnauthorizedException Thrown when the user is not authenticated by OAuth provider.
   */
  public LoginResponse authenticate(Principal principal, HttpSession session)
      throws UnauthorizedException;

  /**
   * 
   * @param session HttpSession object that is used to validate a user's session.
   * @return {@link SessionParams} The attributes present in the session of the user.
   * @throws UnauthorizedException Thrown when the user doesn't have a valid session.
   */
  SessionParams validateSession(HttpSession session) throws UnauthorizedException;

}
