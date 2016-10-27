package com.practo.sai.jobportal.service;

import java.util.List;

import com.practo.sai.jobportal.entities.Team;
import com.practo.sai.jobportal.model.TeamModel;

/**
 * Service that handles are requests regarding {@link Team}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
public interface TeamService {

  /**
   * Method to retrieve all teams in the repository.
   * 
   * @return List of {@link TeamModel}
   */
  public List<TeamModel> getTeams();

}
