package edu.sjsu.cmpe275.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService

{
	@Autowired
	AllServices as; 
	
	public void setAllServices(AllServices as){
		
		this.as = as;
	}

/**

* Retrieves a user record containing the user's credentials and access.

*/
	

@Override
public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException, DataAccessException {
	
	System.out.println("loadUserByUsername");

		final String userIdentifier = username.split("=")[1];
		System.out.println(userIdentifier);
		
			 try{ 
				 
				 UserDetails user = as.getPersonByIdurl(userIdentifier);
				 if(user == null){
					 
					 System.out.println("user is null");
					 throw new UsernameNotFoundException("Error in retrieving user");
				 }
				 else{
					 
					 
					 System.out.println(user.getUsername()); 
				 }
				 return user;
			
			//provide implementation to search user with username in database and
			
			//  return a user of type  UserDetails
			
			} catch (Exception e) {
		
		//if user not found in database throw exception
		
		throw new UsernameNotFoundException("Error in retrieving user");
		
		}
	
	}

} 
