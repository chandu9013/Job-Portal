package com.practo.sai.jobportal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.Filter;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.PageableJobs;
import com.practo.sai.jobportal.model.SessionParams;
import com.practo.sai.jobportal.model.UpdateJobModel;
import com.practo.sai.jobportal.service.AuthenticationService;
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
@RestController
public class JobController {

	private static final Logger LOG = Logger.getInstance(JobController.class);

	@Autowired
	JobService jobService;

	@Autowired
	AuthenticationService authenticationService;

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
	public PageableJobs getJobs(HttpSession session, @PathVariable int perpage, @PathVariable int pageno,
			@RequestParam(value = "cId", required = false) Integer categoryId,
			@RequestParam(value = "tId", required = false) Integer teamId) throws UnauthorizedException {
		LOG.info("Request received to fetch jobs");
		int eId = 14;
		SessionParams params = authenticationService.validateSession(session);
		eId = params.geteId();

		LOG.debug("Filters - CategoryId, TeamId " + categoryId + ",,," + teamId);
		if (eId <= 0) {
			LOG.debug("No session detected. Access denied");
			throw new UnauthorizedException("No session detected. Access denied");
		}
		PageableJobs pageOfJobs = null;
		pageOfJobs = jobService.getJobs(eId, perpage, pageno, new Filter(categoryId, teamId));
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
		authenticationService.validateSession(session);
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
		authenticationService.validateSession(session);
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
		authenticationService.validateSession(session);
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
		authenticationService.validateSession(session);
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
		SessionParams params = authenticationService.validateSession(session);
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
		SessionParams params = authenticationService.validateSession(session);
		int eId = params.geteId();
		return jobService.getMyJobApplications(eId);
	}

	@RequestMapping(value = "/applications/{appId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteJobApplication(@PathVariable int appId, HttpSession session)
			throws BadRequestException, UnauthorizedException {
		LOG.info("Request received to delete a job application - " + appId);
		authenticationService.validateSession(session);
		jobService.deleteJobApplication(appId);
	}

}
