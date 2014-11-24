package edu.sjsu.cmpe275.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSONID")
	private long id;
	
	@Column(name = "FIRSTNAME")
	private String firstname;
	
	@Column(name = "LASTNAME")
	private String lastname;
	
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Embedded
	private Address address;
	
	@ManyToOne
    @JoinColumn(name = "ORGID")
	private Organization org;
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinTable(name="FRIENDSHIP",
    	joinColumns={@JoinColumn(name="PERSONID")},
    	inverseJoinColumns={@JoinColumn(name="FRIENDID")})
    private List<Person> persons = new ArrayList<Person>();

    @ManyToMany(mappedBy="persons")
    private List<Person> friends = new ArrayList<Person>(); 
	
	public Person(){}
	
	public Person(String firstname, String lastname, String email, String description, Address address, Organization org){
		 
		this.firstname = firstname; 
		this.lastname = lastname; 
		this.email = email;
		this.description = description;
		this.address = address;
		this.org = org;
		
	}
	
	public long getId(){return id;}
	public void setId(long id){this.id = id;}
	
	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}
	
	public String getFirstname(){return firstname;}
	public void setFirstname(String firstname){this.firstname = firstname;}
	
	public String getLastname(){return lastname;}
	public void setLastname(String lastname){this.lastname = lastname;}
	
	public String getDescription(){return description;}
	public void setDescription(String description){this.description = description;}
	
	public Address getAddress(){return address;}
	public void setAddress(Address address){this.address = address;}
	
	public Organization getOrg(){return org;}
	public void setOrg(Organization org){this.org = org;}
	
	public List<Person> getFriends(){return friends;}
	public List<Person> getPersons(){return persons;}
	
	public void setFriends(List<Person> friends){this.friends = friends;}
	public void setPersons(List<Person> persons){this.persons = persons;}

}
