package com.practo.sai.jobportal.repo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.utility.Logger;

@Repository
// @Transactional
public class CategoryDaoImpl implements CategoryDao {

  private static final Logger LOG = Logger.getInstance(JobApplicationDaoImpl.class);

  @Autowired
  private SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public List<Category> getCategories() {
    return getSession().createQuery("from Category").list();
  }

}
