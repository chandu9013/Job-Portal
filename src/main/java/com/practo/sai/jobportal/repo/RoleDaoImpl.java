package com.practo.sai.jobportal.repo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practo.sai.jobportal.entities.Employee;
import com.practo.sai.jobportal.entities.Role;
import com.practo.sai.jobportal.entities.UserRole;

/**
 * Dao that deals with all database operations on {@link Role} and {@link UserRole}
 * 
 * @author Sai Chandra Sekhar Dandu
 *
 */
@Repository
public class RoleDaoImpl implements RoleDao {

  @Autowired
  private SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  /**
   * Method that retrieves Role by role name
   */
  @Override
  public Role getRoleByName(String role) {
    DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);

    criteria.add(Restrictions.eq("roleName", role));
    Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
    return (Role) executableCriteria.uniqueResult();
  }

  /**
   * Method that adds a new role for an employee
   */
  @Override
  public void addUserRole(UserRole userRole) {
    getSession().save(userRole);
  }

  /**
   * Method to retrieve role of an employee
   */
  @Override
  public Role getRolebyEmployee(Employee employee) {
    DetachedCriteria criteria = DetachedCriteria.forClass(UserRole.class);

    DetachedCriteria employeeCriteria = criteria.createCriteria("employee");
    employeeCriteria.add(Restrictions.eq("EId", employee.getEId()));
    Criteria executableCriteria = employeeCriteria.getExecutableCriteria(getSession());
    UserRole userRole = (UserRole) executableCriteria.uniqueResult();
    return userRole.getRole();
  }
}
