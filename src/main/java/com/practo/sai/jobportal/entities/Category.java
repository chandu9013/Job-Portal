package com.practo.sai.jobportal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Category Entity.
 */
@Entity
@Table(name = "category", catalog = "job_portal",
    uniqueConstraints = @UniqueConstraint(columnNames = "category_name"))
public class Category implements java.io.Serializable {


  /**
   * Unique Serial ID of the serializable class.
   */
  private static final long serialVersionUID = 3766223563719097041L;
  private Integer CId;
  private String categoryName;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column(name = "c_id", unique = true, nullable = false)
  public Integer getCId() {
    return this.CId;
  }

  public void setCId(Integer CId) {
    this.CId = CId;
  }

  @Column(name = "category_name", unique = true, nullable = false)
  public String getCategoryName() {
    return this.categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

}
