package com.practo.sai.jobportal.model;

public class UpdateJobModel extends AddJobModel {

	private boolean closed;
	private int recruitId;

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public int getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(int recruitId) {
		this.recruitId = recruitId;
	}

}
