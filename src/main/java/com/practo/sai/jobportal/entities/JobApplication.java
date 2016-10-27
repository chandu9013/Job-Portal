package com.practo.sai.jobportal.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 * JobApplication Entity.
 */
@Entity
@Table(name = "job_application", catalog = "job_portal")
@SQLDelete(sql = "UPDATE job_application SET deleted = '1' WHERE j_app_id = ?")
// Filter added to retrieve only records that have not been soft deleted.
@Where(clause = "deleted <> '1'")
public class JobApplication implements java.io.Serializable {

  /**
   * Unique Serial ID of the serializable class.
   */
  private static final long serialVersionUID = 8556578660983865729L;
  private Integer JAppId;
  private Employee employee;
  private Job job;
  private Date appliedOn;
  private char deleted;

  public char getDeleted() {
    return deleted;
  }

  public void setDeleted(char deleted) {
    this.deleted = deleted;
  }


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)

  @Column(name = "j_app_id", unique = true, nullable = false)
  public Integer getJAppId() {
    return this.JAppId;
  }

  public void setJAppId(Integer JAppId) {
    this.JAppId = JAppId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "applied_by", nullable = false)
  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "job_id", nullable = false)
  @Where(clause = "job.deleted <> '1'")
  public Job getJob() {
    return this.job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "applied_on", length = 19)
  public Date getAppliedOn() {
    return this.appliedOn;
  }

  public void setAppliedOn(Date appliedOn) {
    this.appliedOn = appliedOn;
  }

}
