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
import com.practo.sai.jobportal.model.CategoryModel;
import com.practo.sai.jobportal.model.EmployeeModel;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.TeamModel;
import com.practo.sai.jobportal.model.UpdateJobModel;

/**
 * Class that consists of methods that map Entities to/from Response/Request Models
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Service
public class MappingUtility {

  private static final Logger LOG = Logger.getInstance(MappingUtility.class);

  /**
   * Maps List of {@link Job} to {@link JobModel}
   * 
   * @param jobs {@link Job}
   * @return {@link JobModel}
   */
  public List<JobModel> mapToJobModels(List<Job> jobs) {
    LOG.debug("Mapping all Jobs to JobModels to return to user");
    List<JobModel> jobModels = new ArrayList<>();
    for (Job job : jobs) {
      jobModels.add(mapToJobModel(job));
    }
    return jobModels;
  }

  /**
   * Maps {@link Job} to {@link JobModel}
   * 
   * @param job {@link Job}
   * @return {@link JobModel}
   */
  public JobModel mapToJobModel(Job job) {
    JobModel jobModel = new JobModel();
    jobModel.setCategory(mapToCategoryModel(job.getCategory()));
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

  /**
   * Maps {@link AddJobModel} to {@link Job}
   * 
   * @param jobModel {@link AddJobAppModel}
   * @return {@link Job}
   */
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

  /**
   * Maps {@link UpdateJobModel} to {@link Job}
   * 
   * @param jobId Id of the job to be updated
   * @param jobModel Object containing fields to be updated {@link UpdateJobModel}
   * @param job {@link Job}
   * @return
   */
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
    LOG.debug("Team Id - " + jobModel.getTeamId());
    if (jobModel.getTeamId() > 0) {
      Team team = new Team();
      team.setId(jobModel.getTeamId());
      job.setTeam(team);
    }

    return job;
  }

  // /**
  // * Maps {@link AddJobAppModel} to Entity {@link JobApplication}
  // *
  // * @param jobId Id of the job to be updated
  // * @param appModel {@link AddJobAppModel}
  // * @return Entity {@link JobApplication}
  // */
  // public JobApplication mapFromAddJobAppModel(int jobId, AddJobAppModel appModel) {
  // JobApplication application = new JobApplication();
  //
  // Employee applier = new Employee();
  // applier.setEId(appModel.getAppliedBy());
  //
  // application.setEmployee(applier);
  //
  // Job job = new Job();
  // job.setJId(jobId);
  // application.setJob(job);
  //
  // return application;
  // }

  /**
   * 
   * @param application {@link JobApplication}
   * @return {@link JobApplicationModel}
   */
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

  /**
   * Maps List of {@link JobApplication} to {@link JobApplicationModel}
   * 
   * @param applications List of {@link JobApplication}
   * @return List of {@link JobApplicationModel}
   */
  public List<JobApplicationModel> mapToJobAppModels(List<JobApplication> applications) {
    List<JobApplicationModel> jobApplicationModels = new ArrayList<>();
    for (JobApplication application : applications) {
      jobApplicationModels.add(mapToJobAppModel(application));
    }
    LOG.debug("JobApplications mapped to JobApplicationModels to send to user");
    return jobApplicationModels;

  }

  /**
   * Maps List of {@link Team} to {@link TeamModel}
   * 
   * @param teams List of {@link Team}
   * @return List of {@link TeamModel}
   */
  public List<TeamModel> mapToTeamModels(List<Team> teams) {
    LOG.debug("Mapping all Teams to TeamModels to return to user");
    List<TeamModel> teamModels = new ArrayList<>();
    for (Team team : teams) {
      teamModels.add(mapToTeamModel(team));
    }
    return teamModels;
  }

  /**
   * Maps {@link Team} to {@link TeamModel}
   * 
   * @param team {@link Team}
   * @return {@link TeamModel}
   */
  private TeamModel mapToTeamModel(Team team) {
    return new TeamModel(team.getId(), team.getName());
  }

  /**
   * Maps {@link Category} to {@link CategoryModel}
   * 
   * @param category {@link Category}
   * @return {@link CategoryModel}
   */
  public CategoryModel mapToCategoryModel(Category category) {
    return new CategoryModel(category.getCId(), category.getCategoryName());

  }

}
