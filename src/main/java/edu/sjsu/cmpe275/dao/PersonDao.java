package edu.sjsu.cmpe275.dao;

import java.util.List;

import edu.sjsu.cmpe275.model.Person;

public interface PersonDao {

	public Person getPersonById(long id);
    public Person save(Person person);
    public void delete(Person person);
    public List<Person> allPerson();
    public void addFriend(Person p1, Person p2);
    public void rmFriend(Person p1, Person p2); 
}
