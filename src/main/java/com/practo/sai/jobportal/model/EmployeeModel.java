package com.practo.sai.jobportal.model;

public class EmployeeModel {
	private Integer EId;
	private String emailId;
	private String name;

	private RoleModel role;

	public EmployeeModel(Integer eId, String emailId, RoleModel role, String name) {
		super();
		EId = eId;
		this.emailId = emailId;
		this.role = role;
		this.name = name;
	}

	public EmployeeModel(Integer eId, String emailId, String name) {
		super();
		EId = eId;
		this.emailId = emailId;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
