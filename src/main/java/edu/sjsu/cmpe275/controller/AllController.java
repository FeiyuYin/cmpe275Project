package edu.sjsu.cmpe275.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sjsu.cmpe275.model.Address;
import edu.sjsu.cmpe275.model.Organization;
import edu.sjsu.cmpe275.model.Person;
import edu.sjsu.cmpe275.model.Post;
import edu.sjsu.cmpe275.model.Request;
import edu.sjsu.cmpe275.service.AllServices;
@Controller
@RequestMapping("/")
public  class AllController {

	@Autowired
	AllServices as; 
	
	public void setAllServices(AllServices as){
		
		this.as = as;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(){
		
		return "signup_new";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(@RequestParam(value="error", required=false) boolean error, ModelMap model){

		System.out.println("Received request to show login page");

		  if (error == true) {
		   // Assign an error message
		   model.put("error", "You have entered an invalid username or password!");
		  } else {
		   model.put("error", "");
		  }
		return "login";
		//return new ResponseEntity<String>("400, Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Person p = as.getPersonByEmail(email);
		if(p == null || !p.getPassword().equals(password)){
			
			response.sendRedirect("/lab3/login?error=true");
			return;
		}
		else{
			
			System.out.println("Login successfully.");
			HttpSession session = request.getSession();
			session.setAttribute("userSession", session);
			session.setAttribute("email", email);
			response.sendRedirect("/lab3/home");
			return;
		}
			
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String singupGet(@RequestParam(value="error", required=false) String error, ModelMap model){
		
		if(error == null){
			
			model.put("noemail", "");
			model.put("emailexist", "");
		}
		
		else if (error.equals("noemail")) {
			
			model.put("noemail", "Email can not be empty.");
			model.put("emailexist", "");
		}
		else if(error.equals("emailexist")){
			
			model.put("noemail", "");
			model.put("emailexist", "Email already registered.");
		}
		else{
			
			model.put("noemail", "");
			model.put("emailexist", "");
		}
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public void singupPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		if(request.getParameter("email") == null || request.getParameter("email").equals("")){
			
			response.sendRedirect("/lab3/signup?error=noemail");
			return;
		}
		Person tmp = as.getPersonByEmail(request.getParameter("email"));
		if(tmp != null){
			
			response.sendRedirect("/lab3/signup?error=emailexist");
			return;
		}
		else{
			
			String email = request.getParameter("email");
			System.out.println(email);
			String password = request.getParameter("password");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			String zipcode = request.getParameter("zipcode");
			
			Address address = new Address( street,  city,  country,  zipcode);
			
			Person person = new Person( firstname,  lastname,  email, password, null, null, address,  null);
			
			as.createPerson(person);
			response.sendRedirect("/lab3/login");
			return;
		}
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(@RequestParam(value="error", required=false) String error, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException, IOException{
		
		String em = "";
		if(error != null && !error.equals("") && error.equals("nosuchperson")){
			
			em = "No such person.";
		}
		else if(error != null && !error.equals("") && error.equals("alreadyfriends")){
			
			em = "You are already friends.";
		}

		
		System.out.println("/home is required.");
		HttpSession session = request.getSession();
		
		String email = (String) session.getAttribute("email");
		
		if(email == null){return "login";}
		
		System.out.println(email);
		
		ArrayList<Request> requests = (ArrayList<Request>) as.getRequestByTarget(email);
		
		Person person = as.getPersonByEmail(email);
		
		int friendsNum = person.getPersons().size();
		
		ArrayList<Post> posts = (ArrayList<Post>) as.getPostByEmail(email);
		int postsNum = posts.size();
		for(Person p : person.getPersons()){
			
			ArrayList<Post> tmp = (ArrayList<Post>) as.getPostByEmail(p.getEmail());
			posts.addAll(tmp);
		}
		
		model.put("user", email);
		model.put("posts", posts);
		model.put("requests", requests);
		model.put("addpersonerror", em);
		model.put("postsnum", postsNum);
		model.put("friendsnum", friendsNum);
		model.put("friends", person.getPersons());
		//request.setAttribute("user", email);
		//request.setAttribute("posts", posts);

		return "home";
		//request.getRequestDispatcher("/lab3/home.jsp").forward(request, response);
		
		//return new ResponseEntity<String>("400, Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException{

		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
		//return new ResponseEntity<String>("400, Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public void createPost(HttpServletRequest request, HttpServletResponse response) throws IOException{		
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		Person person = as.getPersonByEmail(email);
		String content = request.getParameter("content");
		
		DateFormat dateFormat = new SimpleDateFormat("/MM/dd/yyyy");
		Calendar calobj = Calendar.getInstance();
		String date = dateFormat.format(calobj.getTime());
		
		Post post = new Post( person,  date,  0,  0,  0,  content);
		as.createPost(post);
		
		response.sendRedirect("/lab3/home");
		return;
	}
	
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public void accept(@RequestParam(value="requesterEmail", required=false) String requesterEmail, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		Person person = as.getPersonByEmail(email);
		Person requester = as.getPersonByEmail(requesterEmail);
		as.addFriend(person.getId(), requester.getId());
		
		ArrayList<Request> reqs = (ArrayList<Request>) as.getRequestByTarget(email);
		for(Request req : reqs){
			
			if(req.getRequester().getId() == requester.getId()){
				System.out.println("here");
				req.setStatus("approved");
				as.createRequest(req);
			}
		}
		
		response.sendRedirect("/lab3/home");
		return;
	}
	
	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public void reject(@RequestParam(value="requesterEmail", required=false) String requesterEmail, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		Person person = as.getPersonByEmail(email);
		Person requester = as.getPersonByEmail(requesterEmail);
		
		ArrayList<Request> reqs = (ArrayList<Request>) as.getRequestByTarget(email);
		for(Request req : reqs){
			
			if(req.getRequester().getId() == requester.getId()){
				
				req.setStatus("rejected");
				as.createRequest(req);
			}
		}
		
		response.sendRedirect("/lab3/home");
		return;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if(email == null){
			
			response.sendRedirect("/lab3/login");
			return;
		}
		Person person = as.getPersonByEmail(email);
		Person tmp = as.getPersonByEmail(request.getParameter("newfriend"));
		
		if(tmp == null){
			
			response.sendRedirect("/lab3/home/?error=nosuchperson");
			return;
		}
		else{
			
			for(Person p : person.getPersons()){
				
				if(p.getEmail().equals(request.getParameter("newfriend"))){
					
					response.sendRedirect("/lab3/home/?error=alreadyfriends");
					return;
				}
			}
			
			DateFormat dateFormat = new SimpleDateFormat("/MM/dd/yyyy");
			Calendar calobj = Calendar.getInstance();
			String date = dateFormat.format(calobj.getTime());
			Request req = new Request( person,  tmp,  date,  "pending");
			as.createRequest(req);
			response.sendRedirect("/lab3/home");
			return;
		}
		
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public void remove(@RequestParam(value="id", required=false) long removeId,HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if(email == null){
			
			response.sendRedirect("/lab3/login");
			return;
		}
		Person person = as.getPersonByEmail(email);
		as.rmFriend(person.getId(), removeId);
		response.sendRedirect("/lab3/home");
		return;
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if(email == null){
			
			return "login";
		}
		
		Person person = as.getPersonByEmail(email);
		if(person == null){
			
			return "login";
		}
		
		model.put("firstname", person.getFirstname());
		model.put("lastname", person.getLastname());
		model.put("street", person.getAddress()==null?null:person.getAddress().getStreet());
		model.put("city", person.getAddress()==null?null:person.getAddress().getCity());
		model.put("country", person.getAddress()==null?null:person.getAddress().getCountry());
		model.put("zipcode", person.getAddress()==null?null:person.getAddress().getZip());
		
		return "settings";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		if(email == null){
			
			return "login";
		}
		
		Person person = as.getPersonByEmail(email);
		if(person == null){
			
			return "login";
		}
		
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			String zipcode = request.getParameter("zipcode");
			
			Address address = new Address( street,  city,  country,  zipcode);
			
			person.setAddress(address);
			person.setFirstname(firstname);
			person.setLastname(lastname);
			
			as.createPerson(person);
			return "home";
		
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
	public ResponseEntity<String> createPerson(@RequestParam(value = "email") String email, @RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname, @RequestParam(value = "password") String password,@RequestParam(value = "street") String street, @RequestParam(value = "zipcode") String zipcode, @RequestParam(value = "country") String country, @RequestParam(value = "city") String city, @RequestParam(value = "description") String description, @RequestParam(value = "organization") String organization){
		
		Organization org = as.getOrgById(Long.parseLong(organization));
		if(org == null){
			

			return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);
		}
		
		Address address = new Address( street,  city,  country,  zipcode);
		Person person = new Person(firstname,  lastname,  email, password, null,  description, address,  org);
		Person tmp = as.createPerson(person);
		if(tmp != null){

			return new ResponseEntity<String>(personToJson(tmp), HttpStatus.OK);
		}

		else{return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);}
	}
	
	@RequestMapping(value = "/person/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> updatePerson(@PathVariable("id") String id, @RequestParam(value = "email") String email, @RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname, @RequestParam(value = "password") String password, @RequestParam(value = "street") String street, @RequestParam(value = "zipcode") String zipcode, @RequestParam(value = "country") String country, @RequestParam(value = "city") String city, @RequestParam(value = "description") String description, @RequestParam(value = "organization") String organization){
		
		Organization org = as.getOrgById(Long.parseLong(organization));
		if(org == null){
			

			return new ResponseEntity<String>("400. Invalid request", HttpStatus.BAD_REQUEST);
		}
		
		Address address = new Address( street,  city,  country,  zipcode);
		Person person = new Person(firstname,  lastname,  email, password, null, description,  address,  org);
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

