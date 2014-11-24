package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.model.Person;

@Repository(value = "personDao")
@Transactional
public class PersonDaoImpl implements PersonDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public Person save(Person person) {
		// TODO Auto-generated method stub
		
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(person);
        tx.commit();
        session.close();
        System.out.println(("Person Saved..." + person.getId()));
        return person;
	}

	@Override
	public void delete(Person person) {
		// TODO Auto-generated method stub
		
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(person);
        tx.commit();
        session.close();
        System.out.println(("Person Deleted..." + person.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> allPerson() {
		// TODO Auto-generated method stub
		List<Person> result = null;
		Session session = this.sessionFactory.openSession();

        result = (List<Person>)session.createQuery("from Person").list();

        session.close();
		return result;
	}

	@Override
	public Person getPersonById(long id) {
		// TODO Auto-generated method stub
		
		Session session = this.sessionFactory.openSession();
        Person person = (Person) session.get(Person.class, id);
        session.close();
        System.out.println(("Person Returned..." + person.getId()));
		return person;
	}



	@Override
	public void addFriend(Person p1, Person p2) {
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        p1.getPersons().add(p2);
        p2.getPersons().add(p1);
        session.saveOrUpdate(p1);
//        session.persist(p2);
        tx.commit();
        session.close();
        System.out.println(("Add friendship between " + p1.getId() + " " + p2.getId()));
	}

	@Override
	public void rmFriend(Person p1, Person p2) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for(Person p : p1.getPersons()){
        	
        	if(p.getId() == p2.getId()){
        	
        		
        		p1.getPersons().remove(p);
        		break;
        	}
        }
        
        for(Person p : p2.getPersons()){
        	
        	if(p.getId() == p1.getId()){
        	
        		
        		p2.getPersons().remove(p);
        		break;
        	}
        }

//        p2.getFriends().remove(p1);
//        p2.getPersons().remove(p1);
        session.saveOrUpdate(p1);
        session.saveOrUpdate(p2);
        tx.commit();
        session.close();
        System.out.println(("Remove friendship between " + p1.getId() + " " + p2.getId()));
	}
	
	
}
