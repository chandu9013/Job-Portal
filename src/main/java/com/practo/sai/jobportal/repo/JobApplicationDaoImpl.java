package com.practo.sai.jobportal.repo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.utility.Logger;

/**
 * Class that handles all database operations on {@link JobApplication}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Repository
public class JobApplicationDaoImpl implements JobApplicationDao {

  private static final Logger LOG = Logger.getInstance(JobApplicationDaoImpl.class);

  @Autowired
  private SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  /**
   * Method to add new job application
   */
  @Override
  public void save(JobApplication application) {
    System.out.println("jobApplication --- " + application.getEmployee().getEId());
    getSession().save(application);
  }

  /**
   * Method to get applications mapped to a job.
   */
  @Override
  @SuppressWarnings("unchecked")
  public List<JobApplication> getApplications(Job job) {
    LOG.debug(job.getJId());
    DetachedCriteria criteria = DetachedCriteria.forClass(JobApplication.class);

    DetachedCriteria jobCriteria = criteria.createCriteria("job");
    jobCriteria.add(Restrictions.eq("JId", job.getJId()));
    Criteria executableCriteria = jobCriteria.getExecutableCriteria(getSession());
    return executableCriteria.list();
  }

  /**
   * Method to retrieve an application based on application id.
   */
  @Override
  public JobApplication getApplication(int appId) {
    return getSession().get(JobApplication.class, appId);
  }

  /**
   * Method to delete an existing applications.
   */
  @Override
  public void delete(JobApplication application) {
    getSession().delete(application);
  }


  /**
   * Method to retrieve applications for an employee.
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<JobApplication> getMyApplications(int eId) {
    LOG.debug(eId);
    DetachedCriteria criteria = DetachedCriteria.forClass(JobApplication.class, "ja")
        .createCriteria("ja.job", "j").add(Restrictions.ne("j.deleted", '1'));

    DetachedCriteria employeeCriteria = criteria.createCriteria("ja.employee", "e");
    employeeCriteria.add(Restrictions.eq("e.EId", eId));

    Criteria executableCriteria = employeeCriteria.getExecutableCriteria(getSession());
    return executableCriteria.list();
  }

  @Override
  public void update(JobApplication application) {
    // TODO Auto-generated method stub
  }
}
