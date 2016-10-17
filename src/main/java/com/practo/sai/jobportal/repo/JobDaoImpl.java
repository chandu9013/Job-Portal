package com.practo.sai.jobportal.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Job;

/**
 * DAO Implementation that performs CRUD operations on Job Entity
 * 
 * @author saichandrasekhardandu
 *
 */
@Repository
@Transactional
public class JobDaoImpl implements JobDao {
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
	public List<Job> getJobs() throws JDBCConnectionException {
		return getSession().createQuery("from Job").list();
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
