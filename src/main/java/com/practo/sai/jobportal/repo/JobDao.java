package com.practo.sai.jobportal.repo;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.sai.jobportal.entities.Job;

public interface JobDao {

	public int save(Job job);

	public List<Job> getJobsByAdmin(int eId);

	public Job getJob(int jobId);

	public void update(Job job);

	public void delete(Job job);

	List<Job> getJobsNewForEmployee(int eId) throws JDBCConnectionException;

}
