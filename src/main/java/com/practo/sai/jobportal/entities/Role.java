package com.practo.sai.jobportal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Role Entity.
 */
@Entity
@Table(name = "role", catalog = "job_portal",
    uniqueConstraints = @UniqueConstraint(columnNames = "role_name"))
public class Role implements java.io.Serializable {

  /**
   * Unique Serial ID of the serializable class.
   */
  private static final long serialVersionUID = 7941643635558974267L;
  private Integer RId;
  private String roleName;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column(name = "r_id", unique = true, nullable = false)
  public Integer getRId() {
    return this.RId;
  }

  public void setRId(Integer RId) {
    this.RId = RId;
  }

  @Column(name = "role_name", unique = true, nullable = false)
  public String getRoleName() {
    return this.roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

}
