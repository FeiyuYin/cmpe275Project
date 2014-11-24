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
@Table(name = "COMMENT")
public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMMENTID")
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "POSTID")
	private Post post;
	
	@ManyToOne
    @JoinColumn(name = "PERSONTID")
	private Person person;
	
	@Column(name = "CONTENT")
	private String content; 
	
	@Column(name = "DATE")
	private String date;
	
	public Comment(){}
	
	public Comment(Post post, Person person, String content, String date){
		
		this.post = post; 
		this.person = person;
		this.content = content;
		this.date = date;
	}
	
	public void setId(long id){
		
		this.id = id;
	}
	
	public long getId(){
		
		return this.id;
	}
	
	public void setContent( String content){
		
		this.content = content;
	}
	public String getContent(){
		
		return this.content;
	}
	
	public void setPerson( Person person){
		
		this.person = person;
	}
	public Person getPerson(){
		
		return this.person;
	}
	
	public void setDate(String date){
		
		this.date = date;
	}
	public String getDate(){
		
		return this.date;
	}
	
	public void setPost(Post post){
		
		this.post = post;
	}
	public Post getPost(){
		
		return this.post;
	}
}
