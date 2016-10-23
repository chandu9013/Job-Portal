package com.practo.sai.jobportal.controller;

import java.util.List;

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
import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.PageableJobs;
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
	@RequestMapping(value = "/jobs/{perpage}/{pageno}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public PageableJobs getJobs(HttpSession session, @PathVariable int perpage, @PathVariable int pageno)
			throws UnauthorizedException {
		LOG.info("Request received to fetch jobs");
		int eId = 14;
//		SessionParams params = validateSession(session);
//		eId = params.geteId();
		if (eId <= 0) {
			LOG.debug("No session detected. Access denied");
			throw new UnauthorizedException("No session detected. Access denied");
		}
		PageableJobs pageOfJobs = null;
		pageOfJobs = jobService.getJobs(eId, perpage, pageno);
		LOG.info("Request for all jobs processed succesfully");
		return pageOfJobs;
	}

	/**
	 * Controller Method that handles requests for Adding a new job listing
	 * 
	 * @param job
	 *            Job listing that needs to be added
	 * @return
	 * @throws BadRequestException
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/jobs", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public JobModel addJob(@RequestBody AddJobModel job, HttpSession session)
			throws BadRequestException, UnauthorizedException {
		LOG.info("Request received to add a new job listing");
		SessionParams params = validateSession(session);
		int eId = params.geteId();
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
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.PATCH)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public JobModel updateJob(@PathVariable int jobId, @RequestBody UpdateJobModel jobModel, HttpSession session)
			throws BadRequestException, NotFoundException, UnauthorizedException {
		LOG.info("Request received to update a job listing");
		SessionParams params = validateSession(session);
		int eId = params.geteId();
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
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void deleteJob(@PathVariable int jobId, HttpSession session)
			throws NotFoundException, BadRequestException, UnauthorizedException {
		LOG.info("Request received to deleting a job listing");
		SessionParams params = validateSession(session);
		int eId = params.geteId();
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
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/jobs/{jobId}/applications", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<JobApplicationModel> getJobApplications(@PathVariable int jobId, HttpSession session)
			throws BadRequestException, UnauthorizedException {
		LOG.info("Request received to fetch all applications for jobId - " + jobId);
		SessionParams params = validateSession(session);
		int eId = params.geteId();
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
	 * @throws UnauthorizedException
	 */
	@RequestMapping(value = "/jobs/{jobId}/applications", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public JobApplicationModel addJobApplication(@PathVariable int jobId, HttpSession session)
			throws BadRequestException, UnauthorizedException {
		LOG.info("Request received to add an applications for jobId - " + jobId);
		SessionParams params = validateSession(session);
		int eId = params.geteId();
		AddJobAppModel jobApp = new AddJobAppModel();
		jobApp.setAppliedBy(eId);
		JobApplicationModel applicationModel = jobService.addJobApplication(jobId, jobApp);
		return applicationModel;
	}

	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<JobApplicationModel> getAppliedJobs(HttpSession session)
			throws UnauthorizedException, BadRequestException {
		SessionParams params = validateSession(session);
		int eId = params.geteId();
		return jobService.getMyJobApplications(eId);
	}

	@RequestMapping(value = "/applications/{appId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteJobApplication(@PathVariable int appId, HttpSession session)
			throws BadRequestException, UnauthorizedException {
		LOG.info("Request received to delete a job application - " + appId);
		SessionParams params = validateSession(session);
		int eId = params.geteId();
		jobService.deleteJobApplication(appId);
	}

	@RequestMapping("/categories")
	@ResponseBody
	public List<Category> getCategories(HttpSession session) throws UnauthorizedException {
		List<Category> categories = null;
		LOG.info("Request received to fetch all the categories");
		SessionParams params = validateSession(session);
		int eId = params.geteId();
		categories = jobService.getCategories();
		LOG.info("Request to fetch all the categories processed succesfully");
		return categories;
	}

	@RequestMapping("/teams")
	@ResponseBody
	public List<TeamModel> getTeams(HttpSession session) throws UnauthorizedException {
		List<TeamModel> teams = null;
		LOG.info("Request received to fetch all the teams");
		SessionParams params = validateSession(session);
		int eId = params.geteId();
		teams = jobService.getTeams();
		LOG.info("Request to fetch all the categories processed succesfully");
		return teams;
	}

	private SessionParams validateSession(HttpSession session) throws UnauthorizedException {
		SessionParams sessionParams;
		if (session == null)
			throw new UnauthorizedException("Please login to continue");
		String emailId = (String) session.getAttribute(Constants.SESSION_KEY_EMAIL);
		// String role = (String)
		// session.getAttribute(Constants.SESSION_KEY_ROLE);
		Integer eId = (Integer) session.getAttribute(Constants.SESSION_KEY_EID);
		LOG.debug(eId + ",,," + emailId);
		if (emailId != null && !emailId.equals("") && eId != null) {
			sessionParams = new SessionParams();
			sessionParams.setEmailId(emailId);
			// sessionParams.setRole(role);
			sessionParams.seteId(eId);
			LOG.debug("Session validated for - " + eId);
		} else {
			LOG.error("No session");
			throw new UnauthorizedException("Please login to continue");
		}
		return sessionParams;
	}

}
