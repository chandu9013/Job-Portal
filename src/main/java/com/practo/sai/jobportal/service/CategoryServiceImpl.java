package com.practo.sai.jobportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.repo.CategoryDao;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getCategories() {
		List<Category> categories = null;
		categories = categoryDao.getCategories();
		return categories;
	}

}
