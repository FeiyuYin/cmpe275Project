package edu.sjsu.cmpe275.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REQUEST")
public class Request implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REQUESTID")
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "REQUESTERID")
	private Person requester;
	
	@ManyToOne
    @JoinColumn(name = "TARGETID")
	private Person target;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "STATUS")
	private String status;
	
	public Request(){}
	
	public Request(Person requester, Person target, String date, String status){
		
		this.requester = requester; 
		this.date = date; 
		this.status = status;
		this.target = target;
		
	}
	
	public void setId(long id){
		
		this.id = id;
	}
	
	public long getId(){
		
		return this.id;
	}
	
	public void setRequester(Person p){
		
		this.requester = p;
	}
	
	public Person getRequester(){
		
		return this.requester;
	}
	
	public void setTarget(Person p){
		
		this.target = p;
	}
	
	public Person getTarget(){
		
		return this.target;
	}
	
	public void setDate(String date){
		
		this.date = date;
	}
	
	public String getDate(){
		
		return this.date;
	}
	
	public void setStatus(String status){
		
		this.status = status;
	}
	
	public String getStatus(){
		
		return this.status;
	}
}
