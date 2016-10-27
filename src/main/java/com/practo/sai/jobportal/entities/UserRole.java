package com.practo.sai.jobportal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserRole Entity.
 */
@Entity
@Table(name = "user_role", catalog = "job_portal")
public class UserRole implements java.io.Serializable {

  /**
   * Unique Serial ID of the serializable class.
   */
  private static final long serialVersionUID = 3926081900404327011L;
  private Integer id;
  private Employee employee;
  private Role role;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "e_id", nullable = false)
  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "r_id", nullable = false)
  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
