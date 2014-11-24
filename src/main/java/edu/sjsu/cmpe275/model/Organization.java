package edu.sjsu.cmpe275.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "organization")
public class Organization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORGID")
	private long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Embedded
	private Address address;
	
	public Organization(){}
	
	public Organization(String name, String description, Address address){
		 
		this.name = name; 
		this.description = description; 
		this.address = address;
	}	
	
	public long getId(){return id;}
	public void setId(long id){this.id = id;}
	
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	
	public String getDescription(){return description;}
	public void setDescription(String description){this.description = description;}
	
	public Address getAddress(){return address;}
	public void setAddress(Address address){this.address = address;}
}
