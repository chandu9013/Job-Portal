package com.practo.sai.jobportal.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.model.JobModel;

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

}
