package com.practo.sai.jobportal.service;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.UpdateJobModel;
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
	public JobModel addJob(AddJobModel jobModel) {
		Job job = mUtility.mapAddJob(jobModel);
		jobDao.save(job);
		return mUtility.mapJob(job);
	}

	@Override
	public JobModel updateJob(int jobId, UpdateJobModel jobModel) {
		Job job = mUtility.mapUpdateJob(jobId, jobModel);
		jobDao.update(job);
		return mUtility.mapJob(job);
	}

	@Override
	public void deleteJob(int jobId) {
		Job job = new Job();
		job.setJId(jobId);
		jobDao.delete(job);
	}

	@Override
	public List<JobApplicationModel> getJobApplications(int jobId) {
		List<JobApplication> jobApplications;
		return null;
	}

	@Override
	public void addJobApplication(JobApplication jobApplication) {
		// jobApplicationRepo.save(jobApplication);

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

}
