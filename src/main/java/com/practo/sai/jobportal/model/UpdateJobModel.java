package com.practo.sai.jobportal.model;

public class UpdateJobModel extends AddJobModel {

	private boolean isClosed;
	private int recruitId;

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public int getRecruitId() {
		return recruitId;
	}

	public void setRecruitId(int recruitId) {
		this.recruitId = recruitId;
	}
}
