package com.practo.sai.jobportal.repo;

import java.util.List;

import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;

public interface JobApplicationDao {

  public void save(JobApplication application);

  public List<JobApplication> getApplications(Job job);

  public List<JobApplication> getMyApplications(int eId);

  public JobApplication getApplication(int appId);

  public void update(JobApplication application);

  public void delete(JobApplication application);

}
