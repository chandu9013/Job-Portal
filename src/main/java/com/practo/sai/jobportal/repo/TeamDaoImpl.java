package com.practo.sai.jobportal.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Team;

@Repository
@Transactional
public class TeamDaoImpl implements TeamDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Team> getAll() {
		return getSession().createQuery("from Team").list();
	}

}
