package com.practo.sai.jobportal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.practo.sai.jobportal.model.Category;
import com.practo.sai.jobportal.model.CloseJob;
import com.practo.sai.jobportal.model.Employee;
import com.practo.sai.jobportal.model.Job;
import com.practo.sai.jobportal.model.JobApplication;

public interface AdminService {

	public Page<Job> getJobs(Pageable pageable);

	public Page<JobApplication> getJobApplications(int jobId, Pageable pageable);

	public List<Category> getCategories();

	public Employee getEmployee(String emailId);

	public void addJob(Job job);

	public void addJobApplication(JobApplication jobApplication);

	public void closeJob(CloseJob closeJob);

}
