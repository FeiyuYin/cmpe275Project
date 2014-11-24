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
@Table(name = "POST")
public class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POSTID")
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "PERSONID")
	private Person person;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "views")
	private int views; 
	
	@Column(name = "likes")
	private int likes; 
	
	@Column(name = "comments")
	private int comments; 
	
	@Column(name = "content")
	private String content;
	
	public Post(){}
	
	public Post(Person person, String date, int views, int likes, int comments, String content){
		
		this.person = person; 
		this.date = date; 
		this.likes = likes;
		this.comments = comments; 
		this.content = content;
		this.views = views;
		
	}
	
	public void setId(long id){
		
		this.id = id;
	}
	
	public long getId(){
		
		return this.id;
	}
	
	public void setPerson(Person person){
		
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
	
	public void setViews(int views){
		
		this.views = views;
	}
	public int getViews(){
		
		return this.views;
	}
	
	public void setLikes(int likes){
		
		this.likes = likes;
	}
	public int getLikes(){
		
		return this.likes;
	}
	
	public void setComments(int comments){
		
		this.comments = comments;
	}
	public int getCommetns(){
		
		return this.comments;
	}
	
	public void setContent( String content){
		
		this.content = content;
	}
	public String getContent(){
		
		return this.content;
	}
	
}
