package com.practo.sai.jobportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.SessionParams;
import com.practo.sai.jobportal.model.UpdateJobModel;
import com.practo.sai.jobportal.service.JobService;

/**
 * Controller that provides Rest End-points for the Job Portal
 * 
 * @author Sai Chandra Sekhar Dandu
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
	@ResponseStatus(HttpStatus.OK)
	public List<JobModel> getJobs() {
		List<JobModel> jobModels = null;
		jobModels = jobService.getJobs();

		return jobModels;
	}

	/**
	 * Add a new job listing
	 * 
	 * @param job
	 * @return
	 */
	@RequestMapping(value = "/jobs", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public JobModel addJob(@RequestBody AddJobModel job) {
		return jobService.addJob(job);
	}

	/**
	 * Still fucked up - How to write a generic update method
	 * 
	 * @param jobId
	 * @param jobModel
	 * @return
	 */
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.PATCH)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public JobModel updateJob(@PathVariable int jobId, @RequestBody UpdateJobModel jobModel) {
		return jobService.updateJob(jobId, jobModel);
	}

	/**
	 * Method to delete a specific job from the listings
	 * 
	 * @param jobId
	 * @param jobModel
	 * @return
	 */
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void deleteJob(@PathVariable int jobId) {
		System.out.println("HRERERE");
		jobService.deleteJob(jobId);
	}

	@RequestMapping(value = "/jobs/{jobId}/applications", method = RequestMethod.GET)
	@ResponseBody
	public List<JobApplicationModel> getJobApplications(@PathVariable int jobId) {
		List<JobApplicationModel> jobApplications = null;
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

	// @RequestMapping(value = "/addJobApplication", method =
	// RequestMethod.POST)
	// @ResponseBody
	// public Response addJobApplication(@RequestBody JobApplication
	// jobApplication) {
	// try {
	// jobService.addJobApplication(jobApplication);
	// return sendResponse(Constants.ADD_JOB_APP_SUCCESS,
	// Constants.ADD_JOB_APP_SUCCESS_MESSAGE);
	// } catch (Exception e) {
	//
	// }
	// return sendResponse(Constants.SERVER_ERROR, null);
	// }

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
