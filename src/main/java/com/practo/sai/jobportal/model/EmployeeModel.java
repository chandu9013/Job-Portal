package com.practo.sai.jobportal.model;

public class EmployeeModel {
	private Integer EId;
	private String emailId;

	// private List<UserRoleModel> userRoles;

	public Integer getEId() {
		return EId;
	}

	public EmployeeModel(Integer eId, String emailId) {
		super();
		EId = eId;
		this.emailId = emailId;
	}

	public void setEId(Integer eId) {
		EId = eId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
