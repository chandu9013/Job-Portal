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
 * Controller that provides Rest End-points for the Job Portal.
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@RestController
public class JobController {

  private static final Logger LOG = Logger.getInstance(JobController.class);

  /**
   * {@link JobService}.
   */
  @Autowired
  private JobService jobService;

  /**
   * {@link AuthenticationService}.
   */
  @Autowired
  private AuthenticationService authenticationService;

  /**
   * Controller Method that handles request for fetching all the jobs listed so far
   * 
   * @param session HttpSession object used to validate user session
   * @return List of Jobs {@link PageableJobs}
   * @throws UnauthorizedException Thrown when user doesn't have a valid session
   */
  @RequestMapping(value = "/jobs/{perpage}/{pageno}", method = RequestMethod.GET)
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public PageableJobs getJobs(HttpSession session, @PathVariable int perpage,
      @PathVariable final int pageno,
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
   * Controller Method that handles requests for Adding a new job listing.
   * 
   * @param session HttpSession object used to validate user session
   * @param job {@link AddJobAppModel} Job listing that needs to be added
   * @return {@link JobModel}
   * @throws BadRequestException thrown when required parameters are missing or invalid
   * @throws UnauthorizedException Thrown when User doesn't have a valid Session
   */
  @RequestMapping(value = "/jobs", method = RequestMethod.POST)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public JobModel addJob(@RequestBody AddJobModel job, HttpSession session)
      throws BadRequestException, UnauthorizedException {
    LOG.info("Request received to add a new job listing");
    SessionParams sessionParams = authenticationService.validateSession(session);
    int eId = sessionParams.geteId();
    job.setPostedBy(eId);
    return jobService.addJob(job);
  }

  /**
   * Controller Method that handles requests for updating a Job listing
   * 
   * @param jobId Identifier of the Job that needs to be updated
   * @param jobModel {@link UpdateJobModel}
   * @param session HttpSession object used to validate user session
   * @return {@link JobModel} Consisting of all the updated information
   * @throws BadRequestException Thrown when required parameters are missing or invalid
   * @throws NotFoundException Thrown when the Job referred by jobId is not present
   * @throws UnauthorizedException Thrown when user doesn't have a valid session
   */
  @RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.PATCH)
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public JobModel updateJob(@PathVariable int jobId, @RequestBody UpdateJobModel jobModel,
      HttpSession session) throws BadRequestException, NotFoundException, UnauthorizedException {
    LOG.info("Request received to update a job listing");
    SessionParams sessionParams = authenticationService.validateSession(session);
    int eId = sessionParams.geteId();
    jobModel.setPostedBy(eId);
    return jobService.updateJob(jobId, jobModel);
  }

  /**
   * Controller Method that handles requests to delete a specific job from the listings
   * 
   * @param jobId Identifier of the Job that needs to be deleted
   * @param session HttpSession object used to validate user session
   * @throws NotFoundException Thrown when the Job referred by jobId is not present
   * @throws BadRequestException Thrown when required parameters are missing or invalid
   * @throws UnauthorizedException Thrown when user doesn't have a valid session
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
   * @param jobId Id of the job for which to fetch applications
   * @param session HttpSession object used to validate user session
   * @return List of applications {@link JobApplicationModel}
   * @throws BadRequestException Thrown when required parameters are missing or invalid
   * @throws UnauthorizedException Thrown when user doesn't have a valid session
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
   * Controller method that handles requests for creating a new job application
   * 
   * @param jobId Identifier of the Job
   * @param session HttpSession object used to validate user session
   * @return job application object {@link JobApplicationModel}
   * @throws BadRequestException Thrown when required parameters are missing or invalid
   * @throws UnauthorizedException Thrown when user doesn't have a valid session
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

  /**
   * Controller method that handles requests for fetching all applications for a job
   * 
   * @param session HttpSession object used to validate the user's session
   * @return List of Job applications {@link JobApplicationModel}
   * @throws UnauthorizedException Thrown when user doesn't have a valid session
   * @throws BadRequestException Thrown when required parameters are missing or invalid
   */
  @RequestMapping(value = "/applications", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<JobApplicationModel> getAppliedJobs(HttpSession session)
      throws UnauthorizedException, BadRequestException {
    SessionParams params = authenticationService.validateSession(session);
    int eId = params.geteId();
    return jobService.getMyJobApplications(eId);
  }

  /**
   * Controller method that handles requests for deleting a job application
   * 
   * @param appId Id of the application that is to be deleted
   * @param session HttpSession object used to validate the user's session
   * @throws BadRequestException Thrown when required parameters are missing or invalid
   * @throws UnauthorizedException Thrown when user doesn't have a valid session
   */
  @RequestMapping(value = "/applications/{appId}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void deleteJobApplication(@PathVariable int appId, HttpSession session)
      throws BadRequestException, UnauthorizedException {
    LOG.info("Request received to delete a job application - " + appId);
    authenticationService.validateSession(session);
    jobService.deleteJobApplication(appId);
  }

}
