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
import org.springframework.web.bind.annotation.SessionAttribute;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.SessionParams;
import com.practo.sai.jobportal.model.TeamModel;
import com.practo.sai.jobportal.model.UpdateJobModel;
import com.practo.sai.jobportal.service.JobService;
import com.practo.sai.jobportal.utility.Logger;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;
import inti.ws.spring.exception.client.UnauthorizedException;

/**
 * Controller that provides Rest End-points for the Job Portal
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Controller
public class DataController {

	private static final Logger LOG = Logger.getInstance(DataController.class);

	@Autowired
	JobService jobService;

	/**
	 * Controller Method that handles request for fetching all the jobs listed
	 * so far
	 * 
	 * @return List of Jobs
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<JobModel> getJobs(@SessionAttribute int eId) throws UnauthorizedException {
		LOG.info("Request received to fetch jobs");
		if (eId <= 0) {
			LOG.debug("No session detected. Access denied");
			throw new UnauthorizedException("No session detected. Access denied");
		}
		List<JobModel> jobModels = null;
		jobModels = jobService.getJobs();
		LOG.info("Request for all jobs processed succesfully");
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
		LOG.info("Request received to add a new job listing");
		return jobService.addJob(job);
	}

	/**
	 * Controller Method that handles requests for updating a Job listing
	 * 
	 * @param jobId
	 * @param jobModel
	 * @return
	 * @throws BadRequestException
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.PATCH)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public JobModel updateJob(@PathVariable int jobId, @RequestBody UpdateJobModel jobModel)
			throws BadRequestException, NotFoundException {
		LOG.info("Request received to update a job listing");
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
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void deleteJob(@PathVariable int jobId) throws NotFoundException, BadRequestException {
		LOG.info("Request received to deleting a job listing");
		jobService.deleteJob(jobId);
		LOG.info("Request to delete a job listing processed succesfully");
	}

	/**
	 * Controller Method that handles requests for fetching all job applications
	 * 
	 * @param jobId
	 *            ID of the job for which to fetch applications
	 * @return List of applications
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/jobs/{jobId}/applications", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<JobApplicationModel> getJobApplications(@PathVariable int jobId) throws BadRequestException {
		LOG.info("Request received to fetch all applications for jobId - " + jobId);
		List<JobApplicationModel> jobApplications = null;
		jobApplications = jobService.getJobApplications(jobId);
		LOG.info("Request to fetch all applications for jobId - " + jobId + " processed succesfully");
		LOG.debug(jobApplications.size());
		return jobApplications;
	}

	/**
	 * 
	 * @param jobId
	 * @param jobApp
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/jobs/{jobId}/applications", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public JobApplicationModel addJobApplication(@PathVariable int jobId, @RequestBody AddJobAppModel jobApp)
			throws BadRequestException {
		LOG.info("Request received to add an applications for jobId - " + jobId);
		return jobService.addJobApplication(jobId, jobApp);
	}

	@RequestMapping(value = "/applications/{appId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteJobApplication(@PathVariable int appId) throws BadRequestException {
		LOG.info("Request received to delete a job application - " + appId);
		jobService.deleteJobApplication(appId);
	}

	@RequestMapping("/categories")
	@ResponseBody
	public List<Category> getCategories() {
		List<Category> categories = null;
		LOG.info("Request received to fetch all the categories");
		categories = jobService.getCategories();
		LOG.info("Request to fetch all the categories processed succesfully");
		return categories;
	}

	@RequestMapping("/teams")
	@ResponseBody
	public List<TeamModel> getTeams() {
		List<TeamModel> teams = null;
		LOG.info("Request received to fetch all the categories");
		teams = jobService.getTeams();
		LOG.info("Request to fetch all the categories processed succesfully");
		return teams;
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
