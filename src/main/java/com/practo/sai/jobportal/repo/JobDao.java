package com.practo.sai.jobportal.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Job;

@Repository
@Transactional
public class JobDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public int closeJob(int jobId, int recruitId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Job.class);

		return -1;
	}

	public int save(Job job) {
		getSession().save(job);
		return job.getJId();
	}

	public Job getJob(int jobId) {
		return getSession().get(Job.class, jobId);
	}

	@SuppressWarnings("unchecked")
	public List<Job> getJobs() throws JDBCConnectionException {
		return getSession().createQuery("from Job").list();
	}

}
