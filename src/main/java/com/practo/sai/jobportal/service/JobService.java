package com.practo.sai.jobportal.service;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.UpdateJobModel;

import inti.ws.spring.exception.client.BadRequestException;

public interface JobService {

	public List<JobModel> getJobs() throws JDBCConnectionException;

	public JobModel addJob(AddJobModel job) throws BadRequestException;

	public JobModel updateJob(int jobId, UpdateJobModel jobModel) throws BadRequestException;

	public void deleteJob(int jobId) throws BadRequestException;

	public List<JobApplicationModel> getJobApplications(int jobId) throws BadRequestException;

	public JobApplicationModel addJobApplication(int jobId, AddJobAppModel jobApp) throws BadRequestException;

	public void deleteJobApplication(int appId) throws BadRequestException;

	public List<Category> getCategories();

	public Employee getEmployee(String emailId);

}
