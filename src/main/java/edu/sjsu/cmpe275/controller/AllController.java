package edu.sjsu.cmpe275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import edu.sjsu.cmpe275.model.Address;
import edu.sjsu.cmpe275.model.Organization;
import edu.sjsu.cmpe275.model.Person;
import edu.sjsu.cmpe275.service.AllServices;
@Controller
@RequestMapping("/")
public  class AllController {

	@Autowired
	AllServices as; 
	
	public void setAllServices(AllServices as){
		
		this.as = as;
	}
	
	@RequestMapping(value = "/welcome/", method = RequestMethod.GET)
	public ResponseEntity<String> welcome(){

		return new ResponseEntity<String>("400, Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(){

		return "login";
		//return new ResponseEntity<String>("400, Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getPerson(@PathVariable("id") String id){
		
		Person person = as.getPersonById(Long.parseLong(id));
		if(person != null){

			return new ResponseEntity<String>(personToJson(person), HttpStatus.OK);
		}
		else{
			
			return new ResponseEntity<String>("404. Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseEntity<String> createPerson(@RequestParam(value = "email") String email, @RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname, @RequestParam(value = "street") String street, @RequestParam(value = "zipcode") String zipcode, @RequestParam(value = "country") String country, @RequestParam(value = "city") String city, @RequestParam(value = "description") String description, @RequestParam(value = "organization") String organization){
		
		Organization org = as.getOrgById(Long.parseLong(organization));
		if(org == null){
			

			return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);
		}
		
		Address address = new Address( street,  city,  country,  zipcode);
		Person person = new Person(firstname,  lastname,  email,  description,  address,  org);
		Person tmp = as.createPerson(person);
		if(tmp != null){

			return new ResponseEntity<String>(personToJson(tmp), HttpStatus.OK);
		}

		else{return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);}
	}
	
	@RequestMapping(value = "/person/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> updatePerson(@PathVariable("id") String id, @RequestParam(value = "email") String email, @RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname, @RequestParam(value = "street") String street, @RequestParam(value = "zipcode") String zipcode, @RequestParam(value = "country") String country, @RequestParam(value = "city") String city, @RequestParam(value = "description") String description, @RequestParam(value = "organization") String organization){
		
		Organization org = as.getOrgById(Long.parseLong(organization));
		if(org == null){
			

			return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);
		}
		
		Address address = new Address( street,  city,  country,  zipcode);
		Person person = new Person(firstname,  lastname,  email,  description,  address,  org);
		person.setId(Long.parseLong(id));
		int tmp = as.updatePerson(person);

		if(tmp == 200){
			
			return new ResponseEntity<String>(personToJson(person), HttpStatus.OK);
		}
		else if(tmp == 400){
			
			return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);
		}
		else{return new ResponseEntity<String>("404. Not Found", HttpStatus.NOT_FOUND);}
	}
	
	@RequestMapping(value = "/person/{id}",  method = RequestMethod.DELETE)
	   public ResponseEntity<String>  deleteOnePerson(@PathVariable("id") String id){
		
		Person tmp = as.deletePersonById(Long.parseLong(id));
		if(tmp != null){
			
			return new ResponseEntity<String>(personToJson(tmp), HttpStatus.OK);
		}
		else{
			
			return new ResponseEntity<String>("404. Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@RequestMapping(value = "/org/{id}", method = RequestMethod.GET)
	public ResponseEntity<String>  getOrg(@PathVariable("id") String id){
		
		Organization tmp = as.getOrgById(Long.parseLong(id));
		if(tmp != null){
			
			return new ResponseEntity<String>(orgToJson(tmp), HttpStatus.OK);
		}
		else{return new ResponseEntity<String>("404. Not Found", HttpStatus.NOT_FOUND);}
	}
	
	@RequestMapping(value = "/org", method = RequestMethod.POST)
	public ResponseEntity<String> createOrg(@RequestParam(value = "name") String name, @RequestParam(value = "street") String street, @RequestParam(value = "zipcode") String zipcode, @RequestParam(value = "country") String country, @RequestParam(value = "city") String city, @RequestParam(value = "description") String description){
		
		Address address = new Address(street,  city,  country,  zipcode);
		Organization tmp = new Organization( name,  description,  address);
		Organization org = as.createOrg(tmp);
		if(org != null){

			return new ResponseEntity<String>(orgToJson(tmp), HttpStatus.OK);
		}
		else{return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);}
	}
	
	@RequestMapping(value = "/org/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> updateOrg(@PathVariable("id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "street") String street, @RequestParam(value = "zipcode") String zipcode, @RequestParam(value = "country") String country, @RequestParam(value = "city") String city, @RequestParam(value = "description") String description){
		
		Address address = new Address(street,  city,  country,  zipcode);
		Organization org = new Organization(name,  description,  address);
		org.setId(Long.parseLong(id));
		int tmp = as.updateOrg(org);
		
		if(tmp == 200){
			
			return new ResponseEntity<String>(orgToJson(org), HttpStatus.OK);
		}
		else if(tmp == 400){
			
			return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);
		}
		else{return new ResponseEntity<String>("404. Not Found", HttpStatus.NOT_FOUND);}
	}
	
	@RequestMapping(value = "/org/{id}",  method = RequestMethod.DELETE)
	   public ResponseEntity<String> deleteOneOrg(@PathVariable("id") String id){
		
		Organization org = as.getOrgById(Long.parseLong(id));
		int tmp = as.deleteOrgById(Long.parseLong(id));
		
		if(tmp == 200){
			
			return new ResponseEntity<String>(orgToJson(org), HttpStatus.OK);
		}
		else if(tmp == 400){return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);}
		else{return new ResponseEntity<String>("404. Not Found", HttpStatus.NOT_FOUND);}
	}
	
	@RequestMapping(value = "/friend/{id1}/{id2}",  method = RequestMethod.PUT)
	public ResponseEntity<String> addFriend(@PathVariable("id1") String id1, @PathVariable("id2") String id2){
		
		int result = as.addFriend(Long.parseLong(id1), Long.parseLong(id2));
		if(result == 3){return new ResponseEntity<String>("404. Not Found", HttpStatus.NOT_FOUND);}
		else if(result == 2){return new ResponseEntity<String>("They have beend friends for a long time.", HttpStatus.OK);}
		else{return new ResponseEntity<String>("New friendship between " + id1 + " and " + id2 + " created.", HttpStatus.OK);}
	}
	
	@RequestMapping(value = "/friend/{id1}/{id2}",  method = RequestMethod.DELETE)
	public ResponseEntity<String> rmFriend(@PathVariable("id1") String id1, @PathVariable("id2") String id2){
		
		int result = as.rmFriend(Long.parseLong(id1), Long.parseLong(id2));
		if(result == 404){return new ResponseEntity<String>("404. Not Found", HttpStatus.NOT_FOUND);}
		
		else{return new ResponseEntity<String>("Friendship between " + id1 + " and " + id2 + " deleted.", HttpStatus.OK);}
	}
	
	private String personToJson(Person person){
		
		String friends = "[";
		for(int i = 0; i < person.getPersons().size(); i ++){
			
			if(i < person.getPersons().size()-1){friends += (person.getPersons().get(i).getId() + ", ");}
			else{friends += (person.getPersons().get(i).getId());}
		}
		friends += "]";
		
		String address = "";
		if(person.getAddress() != null){
			
			address = "Street: " + person.getAddress().getStreet() + ", " + "City: " + person.getAddress().getCity() + ", " + "Zipcode: " + person.getAddress().getZip() + ", " + "Country: " + person.getAddress().getCountry() + ", ";
			
		}
		
		String org = "";
		
		if(person.getOrg() != null){
			
			org = "Organization : " + person.getOrg().getId()+ ", ";
		}
		
		String des = "";
		if(person.getDescription() != null){
			
			des = "Description: " + person.getDescription()+ ", ";
		}
		
		String result = "{ " + "id: " + person.getId() + ", " + "Email: " + person.getEmail() + ", " + "Firstname: " + person.getFirstname() + ", " + "Lastname: " + person.getLastname() + ", " + address + des + org  + "Friends: " + friends + "}";  
		
		return result;
	}
	
	private String orgToJson(Organization org){
		
		String des = "";
		if(org.getDescription() != null){
			
			des = "Description: " + org.getDescription()+ ", ";
		}
		
		String address = "";
		if(org.getAddress() != null){
			
			address = "Street: " + org.getAddress().getStreet() + ", " + "City: " + org.getAddress().getCity() + ", " + "Zipcode: " + org.getAddress().getZip() + ", " + "Country: " + org.getAddress().getCountry() + ", ";
			
		}
		
		String result = "{ " + "id: " + org.getId() + ", " + "Nameame: " + org.getName() + ", " + address + des + "}";  
		
		return result;
	}
}

