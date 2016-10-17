package com.practo.sai.jobportal.model;

import java.util.Date;

import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;

public class JobApplicationModel {
	private Integer jAppId;
	private Employee employee;
	private Job job;
	private Date appliedOn;

	public Integer getjAppId() {
		return jAppId;
	}

	public void setjAppId(Integer jAppId) {
		this.jAppId = jAppId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Date getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}

}
