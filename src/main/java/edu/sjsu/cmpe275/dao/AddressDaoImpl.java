package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe275.model.Address;

public class AddressDaoImpl implements AddressDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public Address save(Address address) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(address);
        System.out.println(("Address Saved..." + address.getId()));
        return address;
	}

	@Override
	public void delete(Address address) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(address);
	}

	@Override
	public List<Address> allAddress() {
		// TODO Auto-generated method stub
		
		return sessionFactory.getCurrentSession().createQuery("from address").list();
	}

}
