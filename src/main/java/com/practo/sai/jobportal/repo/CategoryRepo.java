package com.practo.sai.jobportal.repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.practo.sai.jobportal.model.Category;

@Transactional
public interface CategoryRepo extends CrudRepository<Category, Integer> {

}
