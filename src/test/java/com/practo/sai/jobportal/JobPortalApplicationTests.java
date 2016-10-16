package com.practo.sai.jobportal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.practo.sai.jobportal.controller.DataController;
import com.practo.sai.jobportal.model.CloseJob;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobPortalApplicationTests {

	@Autowired
	DataController dataController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void closeJobTest() {
		CloseJob closeJob=new CloseJob();
		closeJob.setJobId(1);
		closeJob.setRecruitId(2);
		dataController.closeJob(closeJob);
	}

}
