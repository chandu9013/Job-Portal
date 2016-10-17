package com.practo.sai.jobportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.model.CloseJob;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.Response;
import com.practo.sai.jobportal.model.SessionParams;
import com.practo.sai.jobportal.service.JobService;

/**
 * Controller that provides Rest End-points for the Job Portal
 * 
 * @author saichandrasekhardandu
 *
 */
@Controller
public class DataController {

	@Autowired
	JobService jobService;

	/**
	 * Method that returns all the jobs listed so far
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	@ResponseBody
	public List<JobModel> getJobs() {
		List<JobModel> jobModels = null;
		try {
			jobModels = jobService.getJobs();
		} catch (JDBCConnectionException jdbcce) {
			jdbcce.printStackTrace();
		}

		return jobModels;
	}

	/**
	 * Add a new job listing
	 * 
	 * @param job
	 * @return
	 */
	@RequestMapping(value = "/jobs", method = RequestMethod.POST)
	public Response addJob(@RequestBody Job job) {
		try {
			jobService.addJob(job);
			return sendResponse(Constants.ADD_JOB_SUCCESS, Constants.ADD_JOB_SUCCESS_MESSAGE);
		} catch (Exception e) {

		}
		return sendResponse(Constants.SERVER_ERROR, null);
	}

	@RequestMapping(value = "/jobs/{jobId}/applications", method = RequestMethod.GET)
	@ResponseBody
	public List<JobApplication> getJobApplications(@PathVariable int jobId) {
		List<JobApplication> jobApplications = null;
		jobApplications = jobService.getJobApplications(jobId);
		return jobApplications;

	}

	@RequestMapping("/categories")
	@ResponseBody
	public List<Category> getCategories() {
		List<Category> categories = null;
		categories = jobService.getCategories();
		return categories;
	}

	@RequestMapping(value = "/addJobApplication", method = RequestMethod.POST)
	@ResponseBody
	public Response addJobApplication(@RequestBody JobApplication jobApplication) {
		try {
			jobService.addJobApplication(jobApplication);
			return sendResponse(Constants.ADD_JOB_APP_SUCCESS, Constants.ADD_JOB_APP_SUCCESS_MESSAGE);
		} catch (Exception e) {

		}
		return sendResponse(Constants.SERVER_ERROR, null);
	}

	@RequestMapping(value = "/closeJob", method = RequestMethod.POST)
	@ResponseBody
	public Response closeJob(@RequestBody CloseJob closeJob) {
		try {
			jobService.closeJob(closeJob);
			return sendResponse(Constants.CLOSE_JOB_SUCCESS, Constants.CLOSE_JOB_SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendResponse(Constants.SERVER_ERROR, null);
	}

	private Response sendResponse(int code, String message) {
		Response response = new Response();
		response.setResponseCode(code);
		response.setMessage(message);
		return response;

	}

	private SessionParams vadilateSession(HttpServletRequest request) {
		SessionParams sessionParams;
		HttpSession session = request.getSession(false);
		if (session != null) {
			String emailId = (String) session.getAttribute(Constants.EMAIL_ID);
			String role = (String) session.getAttribute(Constants.ROLE);
			if (emailId != null && !emailId.equals("") && role != null && !role.equals("")) {
				sessionParams = new SessionParams();
				sessionParams.setEmailId(emailId);
				sessionParams.setRole(role);
			}
		}

		return null;
	}

}
