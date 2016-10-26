package com.practo.sai.jobportal.model;

public class CategoryModel {


  public CategoryModel(int cId, String categoryName) {
    super();
    this.CId = cId;
    this.categoryName = categoryName;
  }

  private int CId;
  private String categoryName;

  public int getCId() {
    return CId;
  }

  public void setCId(int cId) {
    this.CId = cId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

}
