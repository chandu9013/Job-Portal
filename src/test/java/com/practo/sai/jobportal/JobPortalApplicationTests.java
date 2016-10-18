package com.practo.sai.jobportal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.practo.sai.jobportal.model.AddJobModel;
import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.model.UpdateJobModel;
import com.practo.sai.jobportal.service.JobService;

import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobPortalApplicationTests {

	@Autowired
	JobService jobService;

	@Test
	public void contextLoads() {
	}

	@Rollback
	private JobModel addJob() throws BadRequestException {
		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setCategoryId(1);
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);

		return jobModel;
	}

	@Test
	@Rollback(true)
	public void deleteJobTest() throws BadRequestException, NotFoundException {

		JobModel jobModel = addJob();

		// Delete Test
		jobService.deleteJob(jobModel.getjId());
	}

	@Test(expected = NotFoundException.class)
	public void deleteJobInvalidId() throws NotFoundException {
		jobService.deleteJob(-1);
	}

	@Test
	@Rollback(true)
	public void addJobTest() throws BadRequestException {
		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setCategoryId(1);
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);

		assertTrue(jobModel.getjId() > 0);
	}

	@Test(expected = BadRequestException.class)
	public void addJobTestNoCategory() throws BadRequestException {

		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
	}

	@Test(expected = BadRequestException.class)
	public void addJobTestNoDescription() throws BadRequestException {

		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setCategoryId(1);
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
	}

	@Test(expected = BadRequestException.class)
	public void addJobTestNoAdmin() throws BadRequestException {

		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setCategoryId(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
	}

	@Test
	@Rollback(true)
	public void updateJobDescriptionTest() throws BadRequestException {
		JobModel jobModel = addJob();

		UpdateJobModel updateJobModel = new UpdateJobModel();
		updateJobModel.setCategoryId(jobModel.getCategory().getCId());
		updateJobModel.setClosed(false);
		updateJobModel.setDescription("3 Years experienced dev for PHP");
		updateJobModel.setPostedBy(jobModel.getPostedBy().getEId());

		JobModel updatedJobModel = jobService.updateJob(1, updateJobModel);

		assertNotEquals(updatedJobModel.getDescription(), jobModel.getDescription());
	}

	@Test
	@Rollback(true)
	public void updateJobCategoryTest() throws BadRequestException {
		JobModel jobModel = addJob();

		UpdateJobModel updateJobModel = new UpdateJobModel();
		updateJobModel.setCategoryId(2);
		updateJobModel.setClosed(false);
		updateJobModel.setDescription("2 Years experienced dev for PHP");
		updateJobModel.setPostedBy(jobModel.getPostedBy().getEId());

		JobModel updatedJobModel = jobService.updateJob(1, updateJobModel);

		assertNotEquals(updatedJobModel.getCategory().getCId(), jobModel.getCategory().getCId());
	}

	@Test
	@Rollback(true)
	public void updateJobStatusTest() throws BadRequestException {
		JobModel jobModel = addJob();

		UpdateJobModel updateJobModel = new UpdateJobModel();
		updateJobModel.setCategoryId(1);
		updateJobModel.setClosed(true);
		updateJobModel.setDescription("2 Years experienced dev for PHP");
		updateJobModel.setPostedBy(jobModel.getPostedBy().getEId());

		JobModel updatedJobModel = jobService.updateJob(1, updateJobModel);

		assertNotEquals(updatedJobModel.isClosed(), jobModel.isClosed());
	}

	@Test
	public void getAllJobsTest() {
		int size = 11;
		List<JobModel> jobModels = jobService.getJobs();
		assertEquals(size, jobModels.size());
	}

}
