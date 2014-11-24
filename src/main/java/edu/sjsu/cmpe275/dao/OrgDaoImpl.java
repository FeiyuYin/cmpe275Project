package edu.sjsu.cmpe275.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.OrgaDao;
import edu.sjsu.cmpe275.model.Organization;
import edu.sjsu.cmpe275.model.Person;

@Repository(value = "orgDao")
@Transactional
public class OrgDaoImpl implements OrgaDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public Organization save(Organization org) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(org);
        tx.commit();
        session.close();
        System.out.println(("Person Saved..." + org.getId()));
        return org;
	}

	@Override
	public void delete(Organization org) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(org);;
        tx.commit();
        session.close();
        System.out.println(("Org deleted..." + org.getId()));
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> allOrg() {
		// TODO Auto-generated method stub
		List<Organization> result = null;
		Session session = this.sessionFactory.openSession();

        result = (List<Organization>)session.createQuery("from Organization").list();

        session.close();
		return result;
	}

	@Override
	public Organization getOrgById(long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Organization org = (Organization) session.get(Organization.class, id);
        session.close();
        System.out.println(("Person Returned..." + org.getId()));
		return org;
	}

}
