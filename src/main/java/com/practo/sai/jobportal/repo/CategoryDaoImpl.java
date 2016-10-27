package com.practo.sai.jobportal.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Category;

/**
 * Dao Class that deals with all database operations on {@link Category}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

  /**
   * {@link SessionFactory}
   */
  @Autowired
  private SessionFactory sessionFactory;

  /**
   * Method that returns current session.
   * 
   * @return {@link Session}
   */
  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  /**
   * Method that retrieves all categories in the database.
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<Category> getCategories() {
    return getSession().createQuery("from Category").list();
  }

}
