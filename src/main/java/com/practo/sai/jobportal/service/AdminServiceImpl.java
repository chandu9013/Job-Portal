package com.practo.sai.jobportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.model.Category;
import com.practo.sai.jobportal.model.CloseJob;
import com.practo.sai.jobportal.model.Employee;
import com.practo.sai.jobportal.model.Job;
import com.practo.sai.jobportal.model.JobApplication;
import com.practo.sai.jobportal.repo.CategoryRepo;
import com.practo.sai.jobportal.repo.EmployeeRepo;
import com.practo.sai.jobportal.repo.JobApplicationRepo;
import com.practo.sai.jobportal.repo.JobRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	JobRepo jobRepo;

	@Autowired
	JobApplicationRepo jobApplicationRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public Page<Job> getJobs(Pageable pageable) {
		Page<Job> jobs = (Page<Job>) jobRepo.findAll(pageable);
		return jobs;
	}

	public Page<JobApplication> getJobApplications(int jobId, Pageable pageable) {
		Page<JobApplication> jobApplications = jobApplicationRepo.findByJobId(jobId, pageable);
		return jobApplications;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> categories = null;
		categories = (List<Category>) categoryRepo.findAll();
		return categories;
	}

	public Employee getEmployee(String emailId) {
		Employee employee = null;
		employee = employeeRepo.findByEmailId(emailId);
		return employee;
	}

	public void addJob(Job job) {
		jobRepo.save(job);
	}

	@Override
	public void addJobApplication(JobApplication jobApplication) {
		jobApplicationRepo.save(jobApplication);

	}

	@Override
	public void closeJob(CloseJob closeJob) {
		Employee employee = new Employee();
		employee.seteId(closeJob.getJobId());
		jobRepo.closeJob(closeJob.getJobId(), employee);
	}

}
