package com.practo.sai.jobportal.utility;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.model.SessionParams;

import inti.ws.spring.exception.client.UnauthorizedException;

@Component
public class SessionValidator {

	private static final Logger LOG = Logger.getInstance(SessionValidator.class);

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
