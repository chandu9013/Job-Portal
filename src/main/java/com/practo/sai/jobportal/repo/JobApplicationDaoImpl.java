package com.practo.sai.jobportal.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Job;
import com.practo.sai.jobportal.entities.JobApplication;

@Repository
@Transactional
public class JobApplicationDaoImpl implements JobApplicationDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(JobApplication application) {
		System.out.println("jobApplication --- " + application.getEmployee().getEId());
		getSession().save(application);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<JobApplication> getApplications(Job job) {
		DetachedCriteria criteria = DetachedCriteria.forClass(JobApplication.class);

		DetachedCriteria jobCriteria = criteria.createCriteria("job");
		jobCriteria.add(Restrictions.eq("JId", job.getJId()));
		Criteria executableCriteria = jobCriteria.getExecutableCriteria(getSession());
		return executableCriteria.list();
	}

	@Override
	public JobApplication getApplication(int appId) {
		return getSession().get(JobApplication.class, appId);
	}

	@Override
	public void delete(JobApplication application) {
		getSession().delete(application);
	}

	@Override
	public void update(JobApplication application) {
		// TODO Auto-generated method stub

	}
}
