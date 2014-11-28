package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe275.model.Post;
import edu.sjsu.cmpe275.model.Request;

public class RequestDaoImpl implements RequestDao {

	
	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public Request getRequestById(long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Request request = (Request) session.get(Request.class, id);
        session.close();
        System.out.println(("Request Returned..." + request.getId()));
		return request;
	}

	@Override
	public Request save(Request request) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(request);
        tx.commit();
        session.close();
        System.out.println(("Request Saved..." + request.getId()));
        return request;
	}

	@Override
	public void delete(Request request) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(request);
        tx.commit();
        session.close();
        System.out.println(("Request Deleted..." + request.getId()));
	}

	@Override
	public List<Request> allRequest() {
		// TODO Auto-generated method stub
		List<Request> result = null;
		Session session = this.sessionFactory.openSession();

        result = (List<Request>)session.createQuery("from Request").list();

        session.close();
		return result;
	}


	

}
