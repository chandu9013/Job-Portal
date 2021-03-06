package com.practo.sai.jobportal.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

/**
 * Job Entity.
 */
@Entity
@Table(name = "job", catalog = "job_portal")
@SQLDelete(sql = "UPDATE job SET deleted = '1' WHERE j_id = ?")
// Filter added to retrieve only records that have not been soft deleted.
@Where(clause = "deleted <> '1'")
public class Job implements java.io.Serializable {

  /**
   * Unique Serial ID of the serializable class.
   */
  private static final long serialVersionUID = -8256370553504861902L;
  private Integer JId;
  private Category category;
  private Employee employeeByPostedBy;
  private Employee employeeByRecruitId;
  private String description;
  private boolean isClosed;
  private Date lastModified;
  private Date postedOn;
  private char deleted;
  private Team team;
  private Set<JobApplication> jobApplications = new HashSet<JobApplication>(0);

  public char getDeleted() {
    return deleted;
  }

  public void setDeleted(char deleted) {
    this.deleted = deleted;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column(name = "j_id", unique = true, nullable = false)
  public Integer getJId() {
    return this.JId;
  }

  public void setJId(Integer JId) {
    this.JId = JId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "j_c_id", nullable = false)
  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "posted_by", nullable = false)
  public Employee getEmployeeByPostedBy() {
    return this.employeeByPostedBy;
  }

  public void setEmployeeByPostedBy(Employee employeeByPostedBy) {
    this.employeeByPostedBy = employeeByPostedBy;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recruit_id")
  public Employee getEmployeeByRecruitId() {
    return this.employeeByRecruitId;
  }

  public void setEmployeeByRecruitId(Employee employeeByRecruitId) {
    this.employeeByRecruitId = employeeByRecruitId;
  }

  @Column(name = "description", nullable = false)
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "is_closed", nullable = false)
  public boolean isIsClosed() {
    return this.isClosed;
  }

  public void setIsClosed(boolean isClosed) {
    this.isClosed = isClosed;
  }

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_modified", length = 19)
  public Date getLastModified() {
    return this.lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "posted_on", length = 19)
  public Date getPostedOn() {
    return this.postedOn;
  }

  public void setPostedOn(Date postedOn) {
    this.postedOn = postedOn;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "team_id", nullable = false)
  public Team getTeam() {
    return this.team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "job", cascade = CascadeType.ALL)
  public Set<JobApplication> getJobApplications() {
    return this.jobApplications;
  }

  public void setJobApplications(Set<JobApplication> jobApplications) {
    this.jobApplications = jobApplications;
  }

}
