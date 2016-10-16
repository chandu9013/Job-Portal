package com.practo.sai.jobportal.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "job_application")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jAppId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jobId", referencedColumnName = "jId")
	private Job job;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "appliedBy", referencedColumnName = "eId")
	private Employee appliedBy;

	private Timestamp appliedOn;

	public int getjAppId() {
		return jAppId;
	}

	public void setjAppId(int jAppId) {
		this.jAppId = jAppId;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Employee getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(Employee appliedBy) {
		this.appliedBy = appliedBy;
	}

	public Timestamp getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Timestamp appliedOn) {
		this.appliedOn = appliedOn;
	}

}
