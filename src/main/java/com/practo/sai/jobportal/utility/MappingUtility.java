package com.practo.sai.jobportal.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.entities.Category;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.entities.Team;
import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.EmployeeModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.TeamModel;
import com.practo.sai.jobportal.model.UpdateJobModel;

@Service
public class MappingUtility {

	private static final Logger LOG = Logger.getInstance(MappingUtility.class);

	public List<JobModel> mapToJobModels(List<Job> jobs) {
		LOG.debug("Mapping all Jobs to JobModels to return to user");
		List<JobModel> jobModels = new ArrayList<>();
		for (Job job : jobs) {
			jobModels.add(mapToJobModel(job));
		}
		return jobModels;
	}

	public JobModel mapToJobModel(Job job) {
		JobModel jobModel = new JobModel();
		jobModel.setCategory(job.getCategory());
		jobModel.setDescription(job.getDescription());
		jobModel.setjId(job.getJId());
		jobModel.setLastModified(job.getLastModified());

		TeamModel teamModel = new TeamModel(job.getTeam().getId(), job.getTeam().getName());
		jobModel.setTeam(teamModel);

		EmployeeModel postedBy = new EmployeeModel(job.getEmployeeByPostedBy().getEId(),
				job.getEmployeeByPostedBy().getEmailId(), job.getEmployeeByPostedBy().getName());

		jobModel.setPostedBy(postedBy);
		jobModel.setPostedOn(job.getPostedOn());

		EmployeeModel recruit = null;
		if (job.getEmployeeByRecruitId() != null) {
			recruit = new EmployeeModel(job.getEmployeeByRecruitId().getEId(),
					job.getEmployeeByRecruitId().getEmailId(), job.getEmployeeByRecruitId().getName());
			jobModel.setRecruited(recruit);
			jobModel.setClosed(true);
		}
		return jobModel;
	}

	public Job mapFromAddJob(AddJobModel jobModel) {
		Job job = new Job();
		job.setDescription(jobModel.getDescription());

		Category category = new Category();
		category.setCId(jobModel.getCategoryId());
		job.setCategory(category);

		Employee admin = new Employee();
		admin.setEId(jobModel.getPostedBy());
		job.setEmployeeByPostedBy(admin);

		Team team = new Team();
		team.setId(jobModel.getTeamId());
		job.setTeam(team);
		LOG.debug("Mapped from AddJobModel to Job");
		return job;
	}

	public Job mapFromUpdateJob(int jobId, UpdateJobModel jobModel, Job job) {

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
		LOG.debug("isClosed - " + jobModel.isClosed());
		job.setIsClosed(jobModel.isClosed());

		if (jobModel.getRecruitId() > 0) {
			Employee recruit = new Employee();
			recruit.setEId(jobModel.getRecruitId());
			job.setEmployeeByRecruitId(recruit);
		}

		return job;
	}

	public JobApplication mapFromAddJobAppModel(int jobId, AddJobAppModel appModel) {
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

		applicationModel.setEmployee(new EmployeeModel(application.getEmployee().getEId(),
				application.getEmployee().getEmailId(), application.getEmployee().getName()));
		applicationModel.setjAppId(application.getJAppId());
		applicationModel.setJob(mapToJobModel(application.getJob()));
		LOG.debug("Returning applicationModel");
		return applicationModel;
	}

	public List<JobApplicationModel> mapToJobAppModels(List<JobApplication> applications) {
		List<JobApplicationModel> jobApplicationModels = new ArrayList<>();
		for (JobApplication application : applications) {
			jobApplicationModels.add(mapToJobAppModel(application));
		}
		LOG.debug("JobApplications mapped to JobApplicationModels to send to user");
		return jobApplicationModels;

	}

	public List<TeamModel> mapToTeamModels(List<Team> teams) {
		LOG.debug("Mapping all Teams to TeamModels to return to user");
		List<TeamModel> teamModels = new ArrayList<>();
		for (Team team : teams) {
			teamModels.add(mapToTeamModel(team));
		}
		return teamModels;
	}

	private TeamModel mapToTeamModel(Team team) {
		return new TeamModel(team.getId(), team.getName());
	}

}
