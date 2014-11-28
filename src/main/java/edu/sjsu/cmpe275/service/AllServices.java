package edu.sjsu.cmpe275.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe275.dao.CommentDaoImpl;
import edu.sjsu.cmpe275.dao.OrgDaoImpl;
import edu.sjsu.cmpe275.dao.PersonDaoImpl;
import edu.sjsu.cmpe275.dao.PostDaoImpl;
import edu.sjsu.cmpe275.dao.RequestDaoImpl;
import edu.sjsu.cmpe275.model.Organization;
import edu.sjsu.cmpe275.model.Person;
import edu.sjsu.cmpe275.model.Post;
import edu.sjsu.cmpe275.model.Request;

public class AllServices {

	@Autowired
	PersonDaoImpl personDao; 
	@Autowired
	OrgDaoImpl orgDao;
	@Autowired
	PostDaoImpl postDao;
	@Autowired
	CommentDaoImpl commentDao;
	@Autowired
	RequestDaoImpl requestDao;
	
	
	public void setPersonDao(PersonDaoImpl personDao){
		
		this.personDao = personDao;
	}
	
	public void setOrgDao(OrgDaoImpl orgDao){
		
		this.orgDao = orgDao;
	}
	
	public void setPostDao(PostDaoImpl postDao){
		
		this.postDao = postDao;
	}
	
	public void setCommentDao(CommentDaoImpl commentDao){
		
		this.commentDao = commentDao;
	}
	
	public void setRequestDao(RequestDaoImpl requestDao){
		
		this.requestDao = requestDao;
	}
	
	//null if id not exist, otherwise return Person obj 
	public Person getPersonById(long id){
		
		return personDao.getPersonById(id);
		
	}
	
	public Person getPersonByEmail(String email){
		
		List<Person> ap = personDao.allPerson();
		if(ap == null){return null;}
		for(Person p : ap){
			
			if(p.getEmail().equals(email)){
				
				return p;
			}
		}
		return null;
	}
	
	public Person getPersonByIdurl(String idurl){
		
		List<Person> ap = personDao.allPerson();
		if(ap == null){return null;}
		for(Person p : ap){
			System.out.println(p.getIdurl());
			if(p.getIdurl().equals(idurl)){
				
				return p;
			}
		}
		return null;
	}

	
	//return null if id not exist, otherwise Person obj
	public Person deletePersonById(long id){
	
		Person person = personDao.getPersonById(id);
		if(person == null){return null;}
		else{
			
//			List<Person> ap = personDao.allPerson();
//			for(Person p : ap){
//				
//				List<Person> tmp = p.getPersons();
//				for(Person q: tmp){
//					
//					if(q.getId() == id){
//						
//						tmp.remove(q);
//						break;
//					}
//					personDao.save(p);
//				}
//			}
			
			while(person.getPersons().size() > 0){
				
				personDao.rmFriend(person, person.getPersons().get(0));
			}
			personDao.save(person);
			personDao.delete(person);
			return person;
			
		}
	}
	
	//return null if param invalid, otherwise Person obj
	public Person createPerson(Person person){
		
		if(person.getEmail() == null){return null;}
		else{
			
			if(person.getOrg() == null){return personDao.save(person);}
			else{
				
				if(orgDao.getOrgById(person.getOrg().getId()) == null){return null;}
				else{return personDao.save(person);}
			}
		}
	}
	
	//return 404 if id not exist, return 400 if param missing, otherwise return 200
	public int updatePerson(Person person){
		
		
		if(personDao.getPersonById(person.getId()) == null){return 404;}
		else{
			
			if(person.getEmail() == null){return 400;}
			else{
				
				if(person.getOrg() == null){
					
					personDao.save(person);
					return 200;
				}
				else{
					
					if(orgDao.getOrgById(person.getOrg().getId()) == null){return 400;}
					else{
						
						personDao.save(person);
						return 200;
					}
				}
			}
		}
		
	}
	
	
	//return Org obj if id exist, otherwise false
	public Organization getOrgById(long id){
		
		return orgDao.getOrgById(id);
		
		
	}
	
	//return 404 if id not exist, 400 if has person, otherwise 200.
	public int deleteOrgById(long id){
		
		Organization org = orgDao.getOrgById(id);
		if( org == null){return 404;}
		else {
			
			List<Person> tmp = personDao.allPerson();
			for(Person p : tmp){
				
				if(p.getOrg() != null && p.getOrg().getId() == id){return 400;}
			}
			orgDao.delete(org);
			return 200;
		}
		
	}
	
	//return null if wrong param, otherwise Org obj
	public Organization createOrg(Organization org){
		
		orgDao.save(org);
		return org;
		
	}
	
	//return 404 if id not exist, 400 if param missing, otherwise 200
	public int updateOrg(Organization org){
		
		if(org.getId() == 0 || org.getName() == null){return 400;}
		if(orgDao.getOrgById(org.getId()) == null){return 404;}
		else{
			
			orgDao.save(org);
			return 200;
		}		
	}
	
	//return 1 if a new friendship is created, 2 if they were friend, 3 if id not exist
	public int addFriend(long id1, long id2){
		
		Person p1 = personDao.getPersonById(id1);
		Person p2 = personDao.getPersonById(id2);
		if(p1 == null || p2 == null || id1 == id2){return 3;}
		else{
			
			for(Person p : p1.getPersons()){
				
				if(p.getId() == p2.getId()){return 2;}
			}
			
			personDao.addFriend(p1, p2);
			return 1;
		}
	}
	
	//return 200 if rm success, 404 if id not exist, otherwise false 400
	public int rmFriend(long id1, long id2){
		
		Person p1 = personDao.getPersonById(id1);
		Person p2 = personDao.getPersonById(id2);
		if(p1 == null || p2 == null){return 404;}
		else{
			
			for(Person p : p2.getPersons()){
				
				if(p.getId() == p1.getId()){
					
					personDao.rmFriend(p1, p2);
					return 200;
				}
			}
			return 404;
		}
		
	}
	
	public Post createPost(Post post){
		
		postDao.save(post);
		return post;
	}
	
	public List<Post> getPostByEmail(String email){
		
		List<Post> result = new ArrayList<Post>();
		List<Post> posts = postDao.allPost();
		if(posts == null || posts.size() == 0){return null;}
		for(Post post : posts){
			
			if(post.getPerson().getEmail().equals(email)){
				
				result.add(post);
			}
		}
		return result;
	}
	
	public List<Request> getRequestByTarget(String email){
		
		List<Request> result = new ArrayList<Request>();
		List<Request> requests = requestDao.allRequest();
		if(requests == null || requests.size() == 0){return null;}
		for(Request r : requests){
			
			if(r.getTarget().getEmail().equals(email) && r.getStatus().equals("pending")){
				
				result.add(r);
			}
		}
		return result;
	}
	
	public Request createRequest(Request req){
		
		requestDao.save(req);
		return req;
	}
	
}
