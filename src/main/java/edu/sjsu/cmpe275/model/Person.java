package edu.sjsu.cmpe275.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "person")
public class Person implements Serializable, UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSONID")
	private long id;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FIRSTNAME")
	private String firstname;
	
	@Column(name = "LASTNAME")
	private String lastname;
	
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name = "IDURL", unique = true)
	private String idurl;
	
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
	
	public Person(String firstname, String lastname, String email, String password, String idurl, String description, Address address, Organization org){
		 
		this.firstname = firstname; 
		this.lastname = lastname; 
		this.email = email;
		this.password = password;
		this.idurl = idurl;
		this.description = description;
		this.address = address;
		this.org = org;
		
	}
	
	public long getId(){return this.id;}
	public void setId(long id){this.id = id;}
	
	public String getEmail(){return this.email;}
	public void setEmail(String email){this.email = email;}
	
	public String getIdurl(){return this.idurl;}
	public void setIdurl(String idurl){this.idurl = idurl;}
	
	public String getFirstname(){return this.firstname;}
	public void setFirstname(String firstname){this.firstname = firstname;}
	
	public String getLastname(){return this.lastname;}
	public void setLastname(String lastname){this.lastname = lastname;}
	
	public String getDescription(){return this.description;}
	public void setDescription(String description){this.description = description;}
	
	public Address getAddress(){return this.address;}
	public void setAddress(Address address){this.address = address;}
	
	public Organization getOrg(){return this.org;}
	public void setOrg(Organization org){this.org = org;}
	
	public List<Person> getFriends(){return this.friends;}
	public List<Person> getPersons(){return this.persons;}
	
	public void setFriends(List<Person> friends){this.friends = friends;}
	public void setPersons(List<Person> persons){this.persons = persons;}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
