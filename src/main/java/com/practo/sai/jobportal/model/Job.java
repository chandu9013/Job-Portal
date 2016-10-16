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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "job")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "j_c_id", referencedColumnName = "cId")
	private Category category;

	@NotNull
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "posted_by", referencedColumnName = "eId")
	private Employee postedBy;

	private Timestamp postedOn;

	private Timestamp lastModified;

	private boolean isClosed = false;

	@Null
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "j_e_id", referencedColumnName = "eId")
	private Employee recruit;

	public int getjId() {
		return jId;
	}

	public void setjId(int jId) {
		this.jId = jId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(Employee postedBy) {
		this.postedBy = postedBy;
	}

	public Timestamp getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Timestamp postedOn) {
		this.postedOn = postedOn;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Employee getRecruit() {
		return recruit;
	}

	public void setRecruit(Employee recruit) {
		this.recruit = recruit;
	}
}
