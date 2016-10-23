package com.practo.sai.jobportal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.service.AuthenticationService;
import com.practo.sai.jobportal.service.CategoryService;
import com.practo.sai.jobportal.utility.Logger;

import inti.ws.spring.exception.client.UnauthorizedException;

@RestController
public class CategoryController {

	private static final Logger LOG = Logger.getInstance(CategoryController.class);

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getCategories(HttpSession session) throws UnauthorizedException {
		List<Category> categories = null;
		LOG.info("Request received to fetch all the categories");
		authenticationService.validateSession(session);
		categories = categoryService.getCategories();
		LOG.info("Request to fetch all the categories processed succesfully");
		return categories;
	}

}
