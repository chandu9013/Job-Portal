package com.practo.sai.jobportal;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { TestDatabaseConfig.class })
public class CategoryServiceTest {

	@Autowired
	CategoryService categoryService;

	@Test
	public void getCategoriesTest() {
		List<Category> categories = categoryService.getCategories();
		assertEquals(2, categories.size());
	}

}
