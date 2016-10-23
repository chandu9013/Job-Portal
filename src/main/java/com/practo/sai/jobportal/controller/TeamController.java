package com.practo.sai.jobportal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practo.sai.jobportal.model.TeamModel;
import com.practo.sai.jobportal.service.TeamService;
import com.practo.sai.jobportal.utility.Logger;
import com.practo.sai.jobportal.utility.SessionValidator;

import inti.ws.spring.exception.client.UnauthorizedException;

@RestController
public class TeamController {

	private static final Logger LOG = Logger.getInstance(TeamController.class);

	@Autowired
	SessionValidator sessionValidator;

	@Autowired
	TeamService teamService;

	@RequestMapping(value = "/teams", method = RequestMethod.GET)
	@ResponseBody
	public List<TeamModel> getTeams(HttpSession session) throws UnauthorizedException {
		List<TeamModel> teams = null;
		LOG.info("Request received to fetch all the teams");
		sessionValidator.validateSession(session);
		teams = teamService.getTeams();
		LOG.info("Request to fetch all the categories processed succesfully");
		return teams;
	}

}
