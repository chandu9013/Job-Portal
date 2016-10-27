package com.practo.sai.jobportal.repo;

import java.util.List;

import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;

/**
 * Class that handles all database operations on {@link JobApplication}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
public interface JobApplicationDao {

  /**
   * Method to add new job application
   * 
   * @param application {@link JobApplication}
   */
  public void save(JobApplication application);

  /**
   * Method to get applications mapped to a job.
   * 
   * @param job {@link Job}
   * @return List of {@link JobApplication}
   */
  public List<JobApplication> getApplications(Job job);

  /**
   * Method to retrieve applications of an employee
   * 
   * @param eId Employee Id
   * @return List of {@link JobApplication}
   */
  public List<JobApplication> getMyApplications(int eId);

  /**
   * Method to retrieve an application based on application id
   * 
   * @param appId Application Id
   * @return {@link JobApplication}
   */
  public JobApplication getApplication(int appId);

  /**
   * Method to update an application
   * 
   * @param application {@link JobApplication}
   */
  public void update(JobApplication application);

  /**
   * Method to delete an existing application
   * 
   * @param application {@link JobApplication}
   */
  public void delete(JobApplication application);

}
