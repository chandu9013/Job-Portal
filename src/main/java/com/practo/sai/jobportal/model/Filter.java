package com.practo.sai.jobportal.model;

public class Filter {

  private Integer categoryId;

  private Integer teamId;

  public Filter(Integer categoryId, Integer teamId) {
    super();
    this.categoryId = categoryId;
    this.teamId = teamId;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Integer getTeamId() {
    return teamId;
  }

  public void setTeamId(Integer teamId) {
    this.teamId = teamId;
  }

}
