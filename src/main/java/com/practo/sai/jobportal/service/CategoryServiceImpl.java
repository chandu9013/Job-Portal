package com.practo.sai.jobportal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.repo.CategoryDao;

/**
 * Service that handles all requests regarding {@link Category} Entity.
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  /**
   * {@link Category}
   */
  @Autowired
  CategoryDao categoryDao;

  /**
   * Method that fetches all the categories in the respository
   */
  @Transactional
  @Override
  public List<Category> getCategories() {
    List<Category> categories = null;
    categories = categoryDao.getCategories();
    return categories;
  }

}
