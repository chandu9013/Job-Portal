package com.practo.sai.jobportal.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.EmployeeModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.UpdateJobModel;

@Service
public class MappingUtility {

	public List<JobModel> mapJobs(List<Job> jobs) {
		List<JobModel> jobModels = new ArrayList<>();
		for (Job job : jobs) {
			jobModels.add(mapJob(job));
		}
		return jobModels;
	}

	public JobModel mapJob(Job job) {
		JobModel jobModel = new JobModel();
		jobModel.setCategory(job.getCategory());
		jobModel.setDescription(job.getDescription());
		jobModel.setjId(job.getJId());
		jobModel.setLastModified(job.getLastModified());

		EmployeeModel postedBy = new EmployeeModel(job.getEmployeeByPostedBy().getEId(),
				job.getEmployeeByPostedBy().getEmailId());

		jobModel.setPostedBy(postedBy);
		jobModel.setPostedOn(job.getPostedOn());

		EmployeeModel recruit = null;
		if (job.getEmployeeByRecruitId() != null)
			recruit = new EmployeeModel(job.getEmployeeByRecruitId().getEId(),
					job.getEmployeeByRecruitId().getEmailId());
		jobModel.setRecruited(recruit);
		return jobModel;
	}

	public Job mapAddJob(AddJobModel jobModel) {
		Job job = new Job();
		job.setDescription(jobModel.getDescription());

		Category category = new Category();
		category.setCId(jobModel.getCategoryId());
		job.setCategory(category);

		Employee admin = new Employee();
		admin.setEId(jobModel.getPostedBy());
		job.setEmployeeByPostedBy(admin);

		return job;
	}

	public Job mapUpdateJob(int jobId, UpdateJobModel jobModel, Job job) {

		if (jobModel.getDescription() != null)
			job.setDescription(jobModel.getDescription());

		if (jobModel.getCategoryId() > 0) {
			Category category = new Category();
			category.setCId(jobModel.getCategoryId());
			job.setCategory(category);
		}

		// if (jobModel.getPostedBy() > 0) {
		// Employee admin = new Employee();
		// admin.setEId(jobModel.getPostedBy());
		// job.setEmployeeByPostedBy(admin);
		// }
		System.out.println("isClosed - " + jobModel.isClosed());
		job.setIsClosed(jobModel.isClosed());

		if (jobModel.getRecruitId() > 0) {
			Employee recruit = new Employee();
			recruit.setEId(jobModel.getRecruitId());
			job.setEmployeeByRecruitId(recruit);
		}

		return job;
	}

	public JobApplication mapToJobApp(int jobId, AddJobAppModel appModel) {
		JobApplication application = new JobApplication();

		Employee applier = new Employee();
		applier.setEId(appModel.getAppliedBy());

		application.setEmployee(applier);

		Job job = new Job();
		job.setJId(jobId);
		application.setJob(job);

		return application;
	}

	public JobApplicationModel mapToJobAppModel(JobApplication application) {
		JobApplicationModel applicationModel = new JobApplicationModel();
		applicationModel.setAppliedOn(application.getAppliedOn());
		applicationModel.setEmployee(application.getEmployee());
		applicationModel.setjAppId(application.getJAppId());
		applicationModel.setJob(application.getJob());
		return applicationModel;
	}

	public List<JobApplicationModel> mapToJobAppModels(List<JobApplication> applications) {
		List<JobApplicationModel> jobApplicationModels = new ArrayList<>();
		for (JobApplication application : applications) {
			jobApplicationModels.add(mapToJobAppModel(application));
		}
		return jobApplicationModels;

	}

}
