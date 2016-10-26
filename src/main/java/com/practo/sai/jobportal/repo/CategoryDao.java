package com.practo.sai.jobportal.repo;

import java.util.List;

import com.practo.sai.jobportal.entities.Category;

public interface CategoryDao {
  public List<Category> getCategories();
}
