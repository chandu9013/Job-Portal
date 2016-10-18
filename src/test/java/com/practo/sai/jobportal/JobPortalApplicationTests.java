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

	@Test
	@Transactional
	public void deleteJobTest() throws BadRequestException, NotFoundException {

		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setCategoryId(2);
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
		assertTrue(jobModel.getjId() > 0);

	}

	@Test(expected = NotFoundException.class)
	@Transactional
	public void deleteJobInvalidId() throws NotFoundException {
		jobService.deleteJob(-1);
	}

	@Test
	@Transactional
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
	@Transactional
	public void addJobTestNoCategory() throws BadRequestException {

		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
	}

	@Test(expected = BadRequestException.class)
	@Transactional
	public void addJobTestNoDescription() throws BadRequestException {

		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setCategoryId(1);
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
	}

	@Test(expected = BadRequestException.class)
	@Transactional
	public void addJobTestNoAdmin() throws BadRequestException {

		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setCategoryId(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
	}

	@Test
	@Transactional
	public void updateJobDescriptionTest() throws BadRequestException {
		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setCategoryId(1);
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
		assertTrue(jobModel.getjId() > 0);

		UpdateJobModel updateJobModel = new UpdateJobModel();
		updateJobModel.setDescription("3 Years experienced dev for PHP");

		JobModel updatedJobModel = jobService.updateJob(1, updateJobModel);

		assertNotEquals(updatedJobModel.getDescription(), jobModel.getDescription());
	}

	@Test
	@Transactional
	public void updateJobCategoryTest() throws BadRequestException {
		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setCategoryId(1);
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
		assertTrue(jobModel.getjId() > 0);

		UpdateJobModel updateJobModel = new UpdateJobModel();
		updateJobModel.setCategoryId(2);

		JobModel updatedJobModel = jobService.updateJob(1, updateJobModel);
		assertTrue(jobModel.getjId() > 0);

		assertNotEquals(updatedJobModel.getCategory().getCId(), jobModel.getCategory().getCId());
	}

	@Test
	@Transactional
	public void updateJobStatusTest() throws BadRequestException {
		AddJobModel addJobModel = new AddJobModel();
		addJobModel.setDescription("2 Years experienced dev for PHP");
		addJobModel.setCategoryId(1);
		addJobModel.setPostedBy(1);

		// Save and Get Test
		JobModel jobModel = jobService.addJob(addJobModel);
		assertTrue(jobModel.getjId() > 0);

		UpdateJobModel updateJobModel = new UpdateJobModel();
		updateJobModel.setClosed(true);
		updateJobModel.setRecruitId(2);

		JobModel updatedJobModel = jobService.updateJob(1, updateJobModel);

		assertTrue(updatedJobModel.isClosed());
	}

	@Test
	@Transactional
	public void getAllJobsTest() {
		int size = 8;
		List<JobModel> jobModels = jobService.getJobs();
		assertEquals(size, jobModels.size());
	}

}
