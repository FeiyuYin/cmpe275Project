package edu.sjsu.cmpe275.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(name = "ADDRESSID")
	private long id;
	
	@Column(name = "STREET")
	private String street;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "ZIP")
	private String zip;
	
	public Address() {}
 
    public Address(String street, String city, String country, String zip) {
        
        this.street = street;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }
    
    public long getId(){return id;}
    public void setId(long id){this.id = id;}
    
    public String getStreet(){return street;}
    public void setStreet(String street){this.street = street;}
    
    public String getCity(){return city;}
    public void setCity(String city){this.city = city;}
    
    public String getCountry(){return country;}
    public void setCountry(String country){this.country = country;}
    
    public String getZip(){return zip;}
    public void setZip(String zip){this.zip = zip;}
    
    
}
