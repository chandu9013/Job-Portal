package com.practo.sai.jobportal.repo;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.model.Filter;
import com.practo.sai.jobportal.model.PageableJobs;

/**
 * Class that handles database operations on {@link Job}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
public interface JobDao {

  /**
   * Method to add a new job
   * 
   * @param job {@link Job}
   * @return Id of the job
   */
  public int save(Job job);

  /**
   * Method to retrieve jobs added by an admin with employee id eId
   * 
   * @param eId Employee Id
   * @param perpage No. of entries in a page
   * @param pageno Page to retrieve
   * @return {@link PageableJobs}
   */
  public PageableJobs getJobsByAdmin(int eId, int perpage, int pageno);

  /**
   * Method to retrieve a Job based on Id
   * 
   * @param jobId Identifier of a job
   * @return {@link Job}
   */
  public Job getJob(int jobId);

  /**
   * Method to update an existing job
   * 
   * @param job {@link Job}
   */
  public void update(Job job);

  /**
   * Method to delete an existing Job
   * 
   * @param job {@link Job}
   */
  public void delete(Job job);

  /**
   * Method to retrieve jobs open for an employee based on filtering requested
   * 
   * @param eId Employee Id
   * @param perpage Items per page
   * @param pageno Page requested
   * @param filter Set of filters to be applied
   * @return {@link PageableJobs}
   * @throws JDBCConnectionException Thrown when an exception occurs in the database
   */
  public PageableJobs getJobsNewForEmployee(int eId, int perpage, int pageno, Filter filter)
      throws JDBCConnectionException;

}
