package com.practo.sai.jobportal.model;

import java.util.Date;

import com.practo.sai.jobportal.entities.Category;

public class JobModel {
  private Integer jId;
  private CategoryModel category;
  private EmployeeModel postedBy;
  private EmployeeModel recruited;
  private String description;
  private boolean isClosed;
  private Date lastModified;
  private Date postedOn;
  private TeamModel team;

  public Integer getjId() {
    return jId;
  }

  public void setjId(Integer jId) {
    this.jId = jId;
  }

  public EmployeeModel getRecruited() {
    return recruited;
  }

  public void setRecruited(EmployeeModel recruited) {
    this.recruited = recruited;
  }

  public CategoryModel getCategory() {
    return category;
  }

  public void setCategory(CategoryModel category) {
    this.category = category;
  }

  public EmployeeModel getPostedBy() {
    return postedBy;
  }

  public void setPostedBy(EmployeeModel postedBy) {
    this.postedBy = postedBy;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isClosed() {
    return isClosed;
  }

  public void setClosed(boolean isClosed) {
    this.isClosed = isClosed;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public Date getPostedOn() {
    return postedOn;
  }

  public void setPostedOn(Date postedOn) {
    this.postedOn = postedOn;
  }

  public TeamModel getTeam() {
    return team;
  }

  public void setTeam(TeamModel team) {
    this.team = team;
  }
}
