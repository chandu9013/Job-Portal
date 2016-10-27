package com.practo.sai.jobportal.service;

import java.util.List;

import com.practo.sai.jobportal.entities.Category;

/**
 * Service that handles all requests regarding Category Entity.
 * 
 * @author saichandrasekhardandu
 *
 */
public interface CategoryService {

  /**
   * Method that fetches all the categories in the respository
   * 
   * @return List of Category objects
   */
  public List<Category> getCategories();

}
