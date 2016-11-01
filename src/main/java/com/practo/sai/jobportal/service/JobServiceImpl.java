package com.practo.sai.jobportal.service;

import java.util.List;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practo.sai.jobportal.constants.Constants;
import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.entities.Role;
import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.Filter;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.PageableJobs;
import com.practo.sai.jobportal.model.UpdateJobModel;
import com.practo.sai.jobportal.repo.EmployeeDao;
import com.practo.sai.jobportal.repo.JobApplicationDao;
import com.practo.sai.jobportal.repo.JobDao;
import com.practo.sai.jobportal.repo.RoleDao;
import com.practo.sai.jobportal.utility.Logger;
import com.practo.sai.jobportal.utility.MappingUtility;
import com.practo.sai.jobportal.utility.SmtpMailSender;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

/**
 * Service that handles all requests regarding {@link Job} and {@link JobApplication}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Service
public class JobServiceImpl implements JobService {

  /**
   * Logger for this class
   */
  private static final Logger LOG = Logger.getInstance(JobServiceImpl.class);

  /**
   * {@link MappingUtility}
   */
  @Autowired
  MappingUtility mUtility;

  /**
   * {@link SmtpMailSender}
   */
  @Autowired
  SmtpMailSender mailSender;

  /**
   * {@link JobDao}
   */
  @Autowired
  JobDao jobDao;

  /**
   * {@link JobApplicationDao}
   */
  @Autowired
  JobApplicationDao jobApplicationDao;

  /**
   * {@link RoleDao}
   */
  @Autowired
  RoleDao roleDao;

  /**
   * {@link EmployeeDao}
   */
  @Autowired
  EmployeeDao employeeDao;

  /**
   * Method to retrieve jobs posted by an admin or jobs available to an employee
   */
  @Transactional
  @Override
  public PageableJobs getJobs(int eId, int perpage, int pageno, Filter filter)
      throws JDBCConnectionException {
    LOG.info("Servicing request for all jobs");
    PageableJobs pageOfJobs = null;
    Employee employee = new Employee();
    employee.setEId(eId);
    LOG.debug("Paging per page - " + perpage + ", page no - " + pageno);
    Role role = roleDao.getRolebyEmployee(employee);
    if (Constants.ROLE_ADMIN.equalsIgnoreCase(role.getRoleName())) {
      LOG.debug("Getting jobs added by admin - " + employee.getEId());
      pageOfJobs = jobDao.getJobsByAdmin(eId, perpage, pageno);
    } else {
      LOG.debug("Getting all jobs for - " + eId);
      pageOfJobs = jobDao.getJobsNewForEmployee(eId, perpage, pageno, filter);
    }

    List<JobModel> jobModels = mUtility.mapToJobModels(pageOfJobs.getJobEntities());
    LOG.info("Response prepared for user");
    pageOfJobs.setJobs(jobModels);

    // Since Entities shouldn't be exposed to the users
    pageOfJobs.setJobEntities(null);
    return pageOfJobs;

  }

  /**
   * Method to add a job to the database.
   */
  @Transactional
  @Override
  public JobModel addJob(AddJobModel jobModel) throws BadRequestException {
    if (jobModel.getCategoryId() <= 0 || jobModel.getDescription() == null
        || jobModel.getDescription().equals("") || jobModel.getPostedBy() <= 0
        || jobModel.getTeamId() <= 0)
      throw new BadRequestException("Required parameters are either missing or invalid");
    LOG.debug("All the required parameters are present");
    Job job = mUtility.mapFromAddJob(jobModel);
    jobDao.save(job);
    LOG.info("Job added to database succesfully");
    return mUtility.mapToJobModel(job);
  }

  /**
   * Method to update an existing job.
   */
  @Transactional
  @Override
  public JobModel updateJob(int jobId, UpdateJobModel jobModel)
      throws BadRequestException, NotFoundException {
    if (jobId <= 0 || jobModel == null)
      throw new BadRequestException("Required parameters are either missing or invalid");
    LOG.info("Processing request for updating job - " + jobId);
    Job job = jobDao.getJob(jobId);
    if (job == null) {
      LOG.error("Job not found for id - " + jobId);
      throw new NotFoundException("Requested Job doesn't exist");
    }
    mUtility.mapFromUpdateJob(jobId, jobModel, job);
    if (jobModel.getRecruitId() > 0)
      job.setEmployeeByRecruitId(employeeDao.getEmployee(jobModel.getRecruitId()));
    jobDao.update(job);
    if (job.isIsClosed()) {
      // Send Email to Admin as well as applier
      new Thread(new Runnable() {

        @Override
        public void run() {
          try {
            mailSender.sendApproval(job);
          } catch (AuthenticationFailedException e) {
            LOG.error("Authentication error while sending mail", e);
          } catch (MessagingException e) {
            LOG.error("Messaging exception while sending mail", e);
          }
        }
      }).start();
    }

    LOG.info("Update Job processed. Mapping response");
    return mUtility.mapToJobModel(job);
  }

  /**
   * Method to delete an existing job.
   */
  @Transactional
  @Override
  public void deleteJob(int jobId) throws BadRequestException {
    if (jobId <= 0)
      throw new BadRequestException("Required parameters are either missing or invalid");
    LOG.info("Processing request for deleting job - " + jobId);
    Job job = new Job();
    job.setJId(jobId);
    jobDao.delete(job);
  }

  /**
   * Method to retrieve job applications for a given jobId
   */
  @Transactional
  @Override
  public List<JobApplicationModel> getJobApplications(int jobId) throws BadRequestException {
    if (jobId <= 0)
      throw new BadRequestException("Required parameters are either missing or invalid");

    List<JobApplication> jobApplications;
    Job job = new Job();
    job.setJId(jobId);
    jobApplications = jobApplicationDao.getApplications(job);
    LOG.debug("Applications fetched from database - " + jobApplications.size());
    return mUtility.mapToJobAppModels(jobApplications);
  }

  /**
   * Method that fetches all the applications submitted by an employee.
   */
  @Transactional
  @Override
  public List<JobApplicationModel> getMyJobApplications(int eId) throws BadRequestException {
    List<JobApplication> jobApplications;
    jobApplications = jobApplicationDao.getMyApplications(eId);
    LOG.debug("Applications fetched from database - " + jobApplications.size());
    return mUtility.mapToJobAppModels(jobApplications);
  }

  /**
   * Method to submit a new application by an employee.
   */
  @Transactional
  @Override
  public JobApplicationModel addJobApplication(int jobId, AddJobAppModel jobApp)
      throws BadRequestException {
    if (jobApp.getAppliedBy() <= 0 || jobId <= 0)
      throw new BadRequestException("Required parameters are either missing or invalid");
    Employee employee = employeeDao.getEmployee(jobApp.getAppliedBy());
    Job job = jobDao.getJob(jobId);

    JobApplication application = new JobApplication();
    application.setJob(job);
    application.setEmployee(employee);

    jobApplicationDao.save(application);
    LOG.info("Application added to database succefully");

    JobApplicationModel applicationModel = mUtility.mapToJobAppModel(application);

    // Send Email to Admin as well as applier
    new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          mailSender.sendConfirmationMails(applicationModel);
        } catch (AuthenticationFailedException e) {
          LOG.error("Authentication error while sending mail", e);
        } catch (MessagingException e) {
          LOG.error("Messaging exception while sending mail", e);
        }
      }
    }).start();

    return applicationModel;
  }

  /**
   * Method to delete an existing job application
   */
  @Transactional
  @Override
  public void deleteJobApplication(int appId) throws BadRequestException {
    if (appId <= 0)
      throw new BadRequestException("Required parameters are either missing or invalid");
    JobApplication application = new JobApplication();
    application.setJAppId(appId);
    jobApplicationDao.delete(application);

  }

}
