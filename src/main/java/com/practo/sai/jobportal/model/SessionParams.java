package com.practo.sai.jobportal.model;

public class SessionParams {

  private String emailId;

  private String role;

  private int eId;

  public int geteId() {
    return eId;
  }

  public void seteId(int eId) {
    this.eId = eId;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
