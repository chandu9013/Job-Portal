package com.practo.sai.jobportal.service;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.UpdateJobModel;

public interface JobService {

	public List<JobModel> getJobs() throws JDBCConnectionException;

	public JobModel addJob(AddJobModel job);

	public JobModel updateJob(int jobId,UpdateJobModel jobModel);

	public void deleteJob(int jobId);

	public List<JobApplicationModel> getJobApplications(int jobId);

	public List<Category> getCategories();

	public Employee getEmployee(String emailId);

	public void addJobApplication(JobApplication jobApplication);

}
