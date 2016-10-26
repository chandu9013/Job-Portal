package com.practo.sai.jobportal.repo;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.model.Filter;
import com.practo.sai.jobportal.model.PageableJobs;

public interface JobDao {

  public int save(Job job);

  public PageableJobs getJobsByAdmin(int eId, int perpage, int pageno);

  public Job getJob(int jobId);

  public void update(Job job);

  public void delete(Job job);

  public PageableJobs getJobsNewForEmployee(int eId, int perpage, int pageno, Filter filter)
      throws JDBCConnectionException;

}
