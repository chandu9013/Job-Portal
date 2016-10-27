package com.practo.sai.jobportal.model;

import java.util.List;

import com.practo.sai.jobportal.entities.Job;


public class PageableJobs {

  private int totalPages;

  private List<JobModel> jobs;

  private List<Job> jobEntities;

  public PageableJobs(int totalPages, List<Job> jobEntities) {
    super();
    this.totalPages = totalPages;
    this.jobEntities = jobEntities;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public List<JobModel> getJobs() {
    return jobs;
  }

  public void setJobs(List<JobModel> jobs) {
    this.jobs = jobs;
  }

  public List<Job> getJobEntities() {
    return jobEntities;
  }

  public void setJobEntities(List<Job> jobEntities) {
    this.jobEntities = jobEntities;
  }

}
