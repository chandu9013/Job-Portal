package com.practo.sai.jobportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.SessionParams;
import com.practo.sai.jobportal.model.UpdateJobModel;
import com.practo.sai.jobportal.service.JobService;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

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
	 * Controller Method that handles request for fetching all the jobs listed
	 * so far
	 * 
	 * @return List of Jobs
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
	 * Controller Method that handles requests for Adding a new job listing
	 * 
	 * @param job
	 *            Job listing that needs to be added
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/jobs", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public JobModel addJob(@RequestBody AddJobModel job) throws BadRequestException {
		return jobService.addJob(job);
	}

	/**
	 * Controller Method that handles requests for updating a Job listing
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
	 * Controller Method that handles requests to delete a specific job from the
	 * listings
	 * 
	 * @param jobId
	 * @param jobModel
	 * @return
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void deleteJob(@PathVariable int jobId) throws NotFoundException {
		jobService.deleteJob(jobId);
	}

	/**
	 * Controller Method that handles requests for fetching all job applications
	 * 
	 * @param jobId
	 *            ID of the job for which to fetch applications
	 * @return List of applications
	 */
	@RequestMapping(value = "/jobs/{jobId}/applications", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<JobApplicationModel> getJobApplications(@PathVariable int jobId) {
		List<JobApplicationModel> jobApplications = null;
		jobApplications = jobService.getJobApplications(jobId);
		return jobApplications;
	}

	/**
	 * 
	 * @param jobId
	 * @param jobApp
	 * @return
	 */
	@RequestMapping(value = "/jobs/{jobId}/applications", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public JobApplicationModel addJobApplication(@PathVariable int jobId, @RequestBody AddJobAppModel jobApp) {
		return jobService.addJobApplication(jobId, jobApp);
	}

	@RequestMapping(value = "/applications/{appId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteJobApplication(@PathVariable int appId) {
		jobService.deleteJobApplication(appId);
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

	// @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Required
	// parameter either missing or invalid")
	// @ExceptionHandler({ DataIntegrityViolationException.class,
	// IllegalArgumentException.class })
	// public void badRequestExceptions() {
	//
	// }

}
