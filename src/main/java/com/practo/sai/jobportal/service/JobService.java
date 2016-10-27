package com.practo.sai.jobportal.service;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.Filter;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.PageableJobs;
import com.practo.sai.jobportal.model.UpdateJobModel;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

/**
 * Service that handles all requests regarding {@link Job} and {@link JobApplication}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
public interface JobService {

  /**
   * Method to retrieve jobs posted by an admin or jobs available to an employee
   * 
   * @param eId Employee Id
   * @param perpage Number of entries per page.
   * @param pageno Page number to be retrieved
   * @param filter Set of filters that need to be applied to the results
   * @return {@link PageableJobs}
   * @throws JDBCConnectionException Thrown when there is an exception in the database
   */
  public PageableJobs getJobs(int eId, int perpage, int pageno, Filter filter)
      throws JDBCConnectionException;

  /**
   * Method to add a job to the database.
   * 
   * @param job {@link AddJobModel} that contains the particulars about the new job posting.
   * @return {@link JobModel} Succesfully added job model.
   * @throws BadRequestException Thrown when one or more required parameters are missing or invalid
   */
  public JobModel addJob(AddJobModel job) throws BadRequestException;

  /**
   * Method to update an existing job.
   * 
   * @param jobId Id of the Job to be updated
   * @param jobModel {@link UpdateJobModel} Particulars about the job to be updated.
   * @return {@link JobModel} Succesfully updated job model.
   * @throws BadRequestException Thrown when one or more required parameters are missing or invalid.
   * @throws NotFoundException Thrown when the job doesn't exist for the given jobId
   */
  public JobModel updateJob(int jobId, UpdateJobModel jobModel)
      throws BadRequestException, NotFoundException;

  /**
   * Method to delete an existing job.
   * 
   * @param jobId Id of the Job to be deleted
   * @throws BadRequestException Thrown when one or more required parameters are missing or invalid.
   */
  public void deleteJob(int jobId) throws BadRequestException;

  /**
   * Method to retrieve job applications for a given jobId
   * 
   * @param jobId Id of the Job
   * @return List of {@link JobApplicationModel} consisting of all the applications for given jobId.
   * @throws BadRequestException Thrown when one or more required parameters are missing or invalid.
   */
  public List<JobApplicationModel> getJobApplications(int jobId) throws BadRequestException;

  /**
   * Method that fetches all the applications submitted by an employee.
   * 
   * @param eId Employee Id for which applications are to be fetched.
   * @return List of {@link JobApplicationModel}
   * @throws BadRequestException Thrown when one or more required parameters are missing or invalid.
   */
  public List<JobApplicationModel> getMyJobApplications(int eId) throws BadRequestException;

  /**
   * Method to submit a new application by an employee.
   * 
   * @param jobId Id of the Job
   * @param jobApp {@link AddJobAppModel} Consists of the particulars about the application.
   * @return {@link JobApplicationModel} Succesfully submitted job application.
   * @throws BadRequestException Thrown when one or more required parameters are missing or invalid.
   */
  public JobApplicationModel addJobApplication(int jobId, AddJobAppModel jobApp)
      throws BadRequestException;

  /**
   * Method to delete an existing job application
   * 
   * @param appId Application Id of the application that is to be deleted.
   * @throws BadRequestException Thrown when one or more required parameters are missing or invalid.
   */
  public void deleteJobApplication(int appId) throws BadRequestException;

}
