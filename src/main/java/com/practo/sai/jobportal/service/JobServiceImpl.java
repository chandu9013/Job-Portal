package com.practo.sai.jobportal.service;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.model.CloseJob;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.repo.CategoryDao;
import com.practo.sai.jobportal.repo.JobDao;
import com.practo.sai.jobportal.utility.MappingUtility;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	MappingUtility mUtility;

	@Autowired
	JobDao jobDao;

	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<JobModel> getJobs() throws JDBCConnectionException {
		List<Job> jobs = (List<Job>) jobDao.getJobs();
		List<JobModel> jobModels = mUtility.mapJobs(jobs);
		return jobModels;

	}

	@Override
	public List<JobApplication> getJobApplications(int jobId) {
		List<JobApplication> jobApplications;
		return null;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> categories = null;
		categories = categoryDao.getCategories();
		return categories;
	}

	public Employee getEmployee(String emailId) {
		Employee employee = null;
		// employee = employeeRepo.findByEmailId(emailId);
		return employee;
	}

	public void addJob(Job job) {
		jobDao.save(job);
	}

	@Override
	public void addJobApplication(JobApplication jobApplication) {
		// jobApplicationRepo.save(jobApplication);

	}

	@Override
	public void closeJob(CloseJob closeJob) {
		Employee employee = new Employee();
		employee.setEId(closeJob.getJobId());
		// jobDao.closeJob(closeJob.getJobId(), employee);
	}

}
