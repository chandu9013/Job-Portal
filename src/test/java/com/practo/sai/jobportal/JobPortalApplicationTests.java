package com.practo.sai.jobportal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.practo.sai.jobportal.model.JobModel;
import com.practo.sai.jobportal.service.JobService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobPortalApplicationTests {

	@Autowired
	JobService jobService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getAllJobsTest() {
		int size = 2;
		List<JobModel> jobModels = jobService.getJobs();
		assertEquals(size, jobModels.size());
	}

}
