package com.practo.sai.jobportal.service;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.UpdateJobModel;
import com.practo.sai.jobportal.repo.CategoryDao;
import com.practo.sai.jobportal.repo.JobApplicationDao;
import com.practo.sai.jobportal.repo.JobDao;
import com.practo.sai.jobportal.utility.MappingUtility;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	MappingUtility mUtility;

	@Autowired
	JobDao jobDao;

	@Autowired
	JobApplicationDao jobApplicationDao;

	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<JobModel> getJobs() throws JDBCConnectionException {
		List<Job> jobs = (List<Job>) jobDao.getJobs();
		List<JobModel> jobModels = mUtility.mapJobs(jobs);
		return jobModels;

	}

	@Override
	public JobModel addJob(AddJobModel jobModel) throws BadRequestException {
		if (jobModel.getCategoryId() <= 0 || jobModel.getDescription() == null || jobModel.getDescription().equals("")
				|| jobModel.getPostedBy() <= 0)
			throw new BadRequestException("Required parameters are either missing or invalid");

		Job job = mUtility.mapAddJob(jobModel);
		jobDao.save(job);
		return mUtility.mapJob(job);
	}

	@Override
	public JobModel updateJob(int jobId, UpdateJobModel jobModel) {
		Job job = jobDao.getJob(jobId);
		mUtility.mapUpdateJob(jobId, jobModel, job);
		jobDao.update(job);

		System.out.println("Category - " + job.getCategory().getCategoryName());
		return mUtility.mapJob(job);
	}

	@Override
	public void deleteJob(int jobId) throws NotFoundException {
		Job job = new Job();
		job.setJId(jobId);
		jobDao.delete(job);
	}

	@Override
	public List<JobApplicationModel> getJobApplications(int jobId) {
		List<JobApplication> jobApplications;
		Job job = new Job();
		job.setJId(jobId);
		jobApplications = jobApplicationDao.getApplications(job);
		return mUtility.mapToJobAppModels(jobApplications);
	}

	@Override
	public JobApplicationModel addJobApplication(int jobId, AddJobAppModel jobApp) {
		JobApplication application = mUtility.mapToJobApp(jobId, jobApp);
		jobApplicationDao.save(application);
		application = jobApplicationDao.getApplication(application.getJAppId());
		return mUtility.mapToJobAppModel(application);
	}

	@Override
	public void deleteJobApplication(int appId) {
		JobApplication application = new JobApplication();
		application.setJAppId(appId);
		jobApplicationDao.delete(application);

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
