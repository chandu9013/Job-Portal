package com.practo.sai.jobportal.service;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.model.CloseJob;
import com.practo.sai.jobportal.model.JobModel;

public interface JobService {

	public List<JobModel> getJobs() throws JDBCConnectionException;

	public List<JobApplication> getJobApplications(int jobId);

	public List<Category> getCategories();

	public Employee getEmployee(String emailId);

	public void addJob(Job job);

	public void addJobApplication(JobApplication jobApplication);

	public void closeJob(CloseJob closeJob);

}
