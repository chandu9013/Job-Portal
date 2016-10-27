package com.practo.sai.jobportal.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * Employee Entity.
 */
@Entity
@Table(name = "employee", catalog = "job_portal",
    uniqueConstraints = @UniqueConstraint(columnNames = "email_id"))
@SQLDelete(sql = "UPDATE employee SET deleted = '1' WHERE e_id = ?")
// Filter added to retrieve only records that have not been soft deleted.
@Where(clause = "deleted <> '1'")
public class Employee implements java.io.Serializable {

  /**
   * Unique Serial ID of the serializable class.
   */
  private static final long serialVersionUID = 6977986572141134295L;
  private Integer EId;
  private String emailId;
  private char deleted;
  private String name;
  private Set<JobApplication> jobApplications = new HashSet<JobApplication>(0);
  private Set<UserRole> userRoles = new HashSet<UserRole>(0);
  private Set<Job> jobsForPostedBy = new HashSet<Job>(0);
  private Set<Job> jobsForRecruitId = new HashSet<Job>(0);

  public char getDeleted() {
    return deleted;
  }

  public void setDeleted(char deleted) {
    this.deleted = deleted;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column(name = "e_id", unique = true, nullable = false)
  public Integer getEId() {
    return this.EId;
  }

  public void setEId(Integer EId) {
    this.EId = EId;
  }

  @Column(name = "email_id", unique = true, nullable = false)
  public String getEmailId() {
    return this.emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  @Column(name = "name", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
  public Set<JobApplication> getJobApplications() {
    return this.jobApplications;
  }

  public void setJobApplications(Set<JobApplication> jobApplications) {
    this.jobApplications = jobApplications;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
  public Set<UserRole> getUserRoles() {
    return this.userRoles;
  }

  public void setUserRoles(Set<UserRole> userRoles) {
    this.userRoles = userRoles;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeByPostedBy", cascade = CascadeType.ALL)
  public Set<Job> getJobsForPostedBy() {
    return this.jobsForPostedBy;
  }

  public void setJobsForPostedBy(Set<Job> jobsForPostedBy) {
    this.jobsForPostedBy = jobsForPostedBy;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "employeeByRecruitId", cascade = CascadeType.ALL)
  public Set<Job> getJobsForRecruitId() {
    return this.jobsForRecruitId;
  }

  public void setJobsForRecruitId(Set<Job> jobsForRecruitId) {
    this.jobsForRecruitId = jobsForRecruitId;
  }

}
