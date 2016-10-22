package com.practo.sai.jobportal.model;

import java.util.Date;

public class JobApplicationModel {
	private Integer jAppId;
	private EmployeeModel employee;
	private JobModel job;
	private Date appliedOn;

	public Integer getjAppId() {
		return jAppId;
	}

	public void setjAppId(Integer jAppId) {
		this.jAppId = jAppId;
	}

	public EmployeeModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}

	public JobModel getJob() {
		return job;
	}

	public void setJob(JobModel job) {
		this.job = job;
	}

	public Date getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}

}
