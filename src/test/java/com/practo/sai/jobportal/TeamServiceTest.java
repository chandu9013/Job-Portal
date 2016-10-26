package com.practo.sai.jobportal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.practo.sai.jobportal.service.TeamService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes = { TestDatabaseConfig.class })
public class TeamServiceTest {

	@Autowired
	private TeamService teamService;

	@Test
	public void getTeams() {
		assertEquals(4, teamService.getTeams().size());
	}

}
