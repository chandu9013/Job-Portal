package com.practo.sai.jobportal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.practo.sai.jobportal.model.AddJobAppModel;
import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.Filter;
import com.practo.sai.jobportal.model.JobApplicationModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.PageableJobs;
import com.practo.sai.jobportal.model.UpdateJobModel;
import com.practo.sai.jobportal.service.JobService;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes = {TestDatabaseConfig.class})
public class JobServiceTests {

  @Autowired
  JobService jobService;

  ////////////////////// Unit Tests for Job

  @Test
  public void deleteJobTest() throws BadRequestException, NotFoundException {

    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(2);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    jobService.deleteJob(jobModel.getjId());

  }

  @Test(expected = BadRequestException.class)
  public void deleteJobInvalidId() throws BadRequestException {
    jobService.deleteJob(-1);
  }

  @Test(expected = BadRequestException.class)
  public void deleteJobInvalidId2() throws BadRequestException {
    jobService.deleteJob(0);
  }

  @Test
  public void addJobTest() throws BadRequestException {
    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);

    assertTrue(jobModel.getjId() > 0);
  }

  @Test(expected = BadRequestException.class)
  @Transactional
  @Rollback(true)
  public void addJobTestNoCategory() throws BadRequestException {

    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    jobService.addJob(addJobModel);
  }

  @Test(expected = BadRequestException.class)
  public void addJobTestNoDescription() throws BadRequestException {

    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    // Save and Get Test
    jobService.addJob(addJobModel);
  }

  @Test(expected = BadRequestException.class)
  public void addJobTestNoAdmin() throws BadRequestException {

    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setTeamId(1);

    // Save and Get Test
    jobService.addJob(addJobModel);
  }

  @Test(expected = BadRequestException.class)
  public void addJobTestNoTeam() throws BadRequestException {

    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);

    // Save and Get Test
    jobService.addJob(addJobModel);
  }

  @Test
  public void updateJobDescriptionTest() throws BadRequestException, NotFoundException {
    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    UpdateJobModel updateJobModel = new UpdateJobModel();
    updateJobModel.setDescription("3 Years experienced dev for PHP");

    JobModel updatedJobModel = jobService.updateJob(jobModel.getjId(), updateJobModel);

    assertNotEquals(updatedJobModel.getDescription(), jobModel.getDescription());
  }

  @Test
  public void updateJobCategoryTest() throws BadRequestException, NotFoundException {
    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    UpdateJobModel updateJobModel = new UpdateJobModel();
    updateJobModel.setCategoryId(4);

    JobModel updatedJobModel = jobService.updateJob(jobModel.getjId(), updateJobModel);
    assertTrue(jobModel.getjId() > 0);

    assertNotEquals(updatedJobModel.getCategory().getCId(), jobModel.getCategory().getCId());
  }

  @Test
  public void updateJobStatusTest() throws BadRequestException, NotFoundException {
    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);;

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    UpdateJobModel updateJobModel = new UpdateJobModel();
    updateJobModel.setClosed(true);
    updateJobModel.setRecruitId(150);

    JobModel updatedJobModel = jobService.updateJob(jobModel.getjId(), updateJobModel);
    assertTrue(updatedJobModel.isClosed());
  }

  @Test(expected = BadRequestException.class)
  public void updateJobNoJobId() throws BadRequestException, NotFoundException {
    jobService.updateJob(0, null);
  }

  @Test(expected = BadRequestException.class)
  public void updateJobNoJob() throws BadRequestException, NotFoundException {
    jobService.updateJob(1500, null);
  }

  @Test
  public void getAllJobsTest() throws BadRequestException {
    PageableJobs pageOfJobs = jobService.getJobs(100, 100, 1, null);
    int size = pageOfJobs.getJobs().size();

    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    pageOfJobs = jobService.getJobs(100, 100, 1, null);
    assertEquals(size + 1, pageOfJobs.getJobs().size());
  }

  @Test
  public void getJobsForEmployee() {
    Filter filter = new Filter(4, 1);
    PageableJobs pageOfJobs = jobService.getJobs(150, 100, 1, filter);
    pageOfJobs.getJobs().size();
  }

  ///////////////////////// Unit Tests for JobApplication

  @Test
  public void addJobApplication() throws BadRequestException {
    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    AddJobAppModel addJobAppModel = new AddJobAppModel();
    addJobAppModel.setAppliedBy(150);

    jobService.addJobApplication(jobModel.getjId(), addJobAppModel);
  }

  @Test(expected = BadRequestException.class)
  public void addJobApplicationNoEmployee() throws BadRequestException {
    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    AddJobAppModel addJobAppModel = new AddJobAppModel();

    jobService.addJobApplication(jobModel.getjId(), addJobAppModel);
  }

  @Test(expected = BadRequestException.class)
  public void addJobApplicationNoJob() throws BadRequestException {
    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    AddJobAppModel addJobAppModel = new AddJobAppModel();

    jobService.addJobApplication(-1, addJobAppModel);
  }

  @Test
  public void deleteJobApplication() throws BadRequestException {
    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    AddJobAppModel addJobAppModel = new AddJobAppModel();
    addJobAppModel.setAppliedBy(150);

    JobApplicationModel jobApplicationModel =
        jobService.addJobApplication(jobModel.getjId(), addJobAppModel);

    jobService.deleteJobApplication(jobApplicationModel.getjAppId());

  }

  @Test(expected = BadRequestException.class)
  public void deleteJobApplicationNotExists() throws BadRequestException {
    jobService.deleteJobApplication(-1);
  }

  @Test(expected = BadRequestException.class)
  public void deleteJobApplicationNotExists2() throws BadRequestException {
    jobService.deleteJobApplication(0);
  }

  @Test
  public void getJobApplications() throws BadRequestException {

    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    List<JobApplicationModel> jobApplicationModels =
        jobService.getJobApplications(jobModel.getjId());
    int size = jobApplicationModels.size();

    AddJobAppModel addJobAppModel = new AddJobAppModel();
    addJobAppModel.setAppliedBy(150);

    jobService.addJobApplication(jobModel.getjId(), addJobAppModel);

    jobApplicationModels = jobService.getJobApplications(jobModel.getjId());

    assertEquals(size + 1, jobApplicationModels.size());
  }

  @Test
  public void getMyApplications() throws BadRequestException {
    AddJobModel addJobModel = new AddJobModel();
    addJobModel.setDescription("2 Years experienced dev for PHP");
    addJobModel.setCategoryId(1);
    addJobModel.setPostedBy(100);
    addJobModel.setTeamId(1);

    // Save and Get Test
    JobModel jobModel = jobService.addJob(addJobModel);
    assertTrue(jobModel.getjId() > 0);

    List<JobApplicationModel> jobApplicationModels = jobService.getMyJobApplications(150);
    int size = jobApplicationModels.size();

    AddJobAppModel addJobAppModel = new AddJobAppModel();
    addJobAppModel.setAppliedBy(150);

    jobService.addJobApplication(jobModel.getjId(), addJobAppModel);

    jobApplicationModels = jobService.getMyJobApplications(150);

    assertEquals(size + 1, jobApplicationModels.size());
  }

}
