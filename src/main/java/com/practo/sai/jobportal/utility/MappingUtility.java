package com.practo.sai.jobportal.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.model.AddJobModel;
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
		jobModel.setPostedBy(job.getEmployeeByPostedBy());
		jobModel.setPostedOn(job.getPostedOn());
		jobModel.setRecruitId(job.getEmployeeByRecruitId());
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

	public Job mapUpdateJob(int jobId, UpdateJobModel jobModel) {
		Job job = new Job();
		job.setJId(jobId);
		job.setDescription(jobModel.getDescription());

		if (jobModel.getCategoryId() > 0) {
			Category category = new Category();
			category.setCId(jobModel.getCategoryId());
			job.setCategory(category);
		}

		if (jobModel.getPostedBy() > 0) {
			Employee admin = new Employee();
			admin.setEId(jobModel.getPostedBy());
			job.setEmployeeByPostedBy(admin);
		}

		job.setIsClosed(jobModel.isClosed());

		if (jobModel.getPostedBy() > 0) {
			Employee recruit = new Employee();
			recruit.setEId(jobModel.getRecruitId());
			job.setEmployeeByRecruitId(recruit);
		}

		return job;
	}

}
