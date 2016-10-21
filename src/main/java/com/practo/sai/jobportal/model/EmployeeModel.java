package com.practo.sai.jobportal.model;

public class EmployeeModel {
	private Integer EId;
	private String emailId;

	private RoleModel role;

	public EmployeeModel(Integer eId, String emailId, RoleModel role) {
		super();
		EId = eId;
		this.emailId = emailId;
		this.role = role;
	}

	public EmployeeModel(Integer eId, String emailId) {
		super();
		EId = eId;
		this.emailId = emailId;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public Integer getEId() {
		return EId;
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
