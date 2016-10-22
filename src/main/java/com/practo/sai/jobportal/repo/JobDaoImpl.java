package com.practo.sai.jobportal.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;
import com.practo.sai.jobportal.utility.Logger;

/**
 * DAO Implementation that performs CRUD operations on Job Entity
 * 
 * @author saichandrasekhardandu
 *
 */
@Repository
@Transactional
public class JobDaoImpl implements JobDao {

	private static final Logger LOG = Logger.getInstance(JobDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int save(Job job) {
		getSession().save(job);
		return job.getJId();
	}

	@Override
	public Job getJob(int jobId) {
		return getSession().get(Job.class, jobId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJobsNewForEmployee(int eId) throws JDBCConnectionException {
		// Query query = getSession().createQuery(
		// "from Job where JId NOT IN (Select job.JId from JobApplication where
		// employee.EId = :eId)");
		// query.setParameter("eId", eId);

		DetachedCriteria application = DetachedCriteria.forClass(JobApplication.class)
				.setProjection(Property.forName("job.JId")).createCriteria("employee").add(Restrictions.eq("EId", eId));

		DetachedCriteria job = DetachedCriteria.forClass(Job.class, "j");

		job.add(Property.forName("JId").notIn(application));
		return job.getExecutableCriteria(getSession()).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJobsByAdmin(int eId) throws JDBCConnectionException {
		// return getSession().createQuery("from Job").list();

		DetachedCriteria criteria = DetachedCriteria.forClass(Job.class);

		DetachedCriteria employeeCriteria = criteria.createCriteria("employeeByPostedBy");
		employeeCriteria.add(Restrictions.eq("EId", eId));
		Criteria executableCriteria = employeeCriteria.getExecutableCriteria(getSession());
		return executableCriteria.list();
	}

	@Override
	public void update(Job job) {
		getSession().update(job);
	}

	@Override
	public void delete(Job job) {
		getSession().delete(job);
	}

}
