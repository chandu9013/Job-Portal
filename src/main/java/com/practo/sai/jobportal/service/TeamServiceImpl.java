package com.practo.sai.jobportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Team;
import com.practo.sai.jobportal.model.TeamModel;
import com.practo.sai.jobportal.repo.TeamDao;
import com.practo.sai.jobportal.utility.MappingUtility;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	MappingUtility mUtility;

	@Autowired
	TeamDao teamDao;

	@Override
	public List<TeamModel> getTeams() {
		List<Team> teams = teamDao.getAll();
		return mUtility.mapToTeamModels(teams);
	}

}
