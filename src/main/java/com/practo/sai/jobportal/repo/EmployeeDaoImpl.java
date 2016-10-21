package com.practo.sai.jobportal.repo;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.JobApplication;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Employee employee) {
		getSession().save(employee);
	}

	@Override
	public Employee getEmployee(int eId) {
		return getSession().get(Employee.class, eId);
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Employee.class);

		criteria.add(Restrictions.eq("emailId", email));
		Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
		return (Employee) executableCriteria.uniqueResult();
	}

}
