package com.practo.sai.jobportal.repo;

import java.util.List;

import com.practo.sai.jobportal.entities.Team;

/**
 * DAO that deals with database operations on {@link Team}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
public interface TeamDao {

  /**
   * Method to retrieve all teams
   * 
   * @return List of {@link Team}
   */
  public List<Team> getAll();

}
