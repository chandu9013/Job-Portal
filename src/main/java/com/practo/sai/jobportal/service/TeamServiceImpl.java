package com.practo.sai.jobportal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Team;
import com.practo.sai.jobportal.model.TeamModel;
import com.practo.sai.jobportal.repo.TeamDao;
import com.practo.sai.jobportal.utility.MappingUtility;

/**
 * Service that handles are requests regarding {@link Team}
 * 
 * @author saichandrasekhardandu
 *
 */
@Service
public class TeamServiceImpl implements TeamService {

  /**
   * {@link MappingUtility}
   */
  @Autowired
  MappingUtility mUtility;

  /**
   * {@link TeamDao}
   */
  @Autowired
  TeamDao teamDao;

  /**
   * Method to retrieve all teams in the repository.
   */
  @Transactional
  @Override
  public List<TeamModel> getTeams() {
    List<Team> teams = teamDao.getAll();
    return mUtility.mapToTeamModels(teams);
  }

}
