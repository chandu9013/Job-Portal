package com.practo.sai.jobportal.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Team;

/**
 * DAO that deals with database operations on {@link Team}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Repository
public class TeamDaoImpl implements TeamDao {

  @Autowired
  private SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  /**
   * Method to retrieve all teams
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<Team> getAll() {
    return getSession().createQuery("from Team").list();
  }

}
