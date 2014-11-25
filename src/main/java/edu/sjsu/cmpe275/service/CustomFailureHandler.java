package edu.sjsu.cmpe275.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import edu.sjsu.cmpe275.model.Person;

public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	AllServices as;
	
	public void setAllServices(AllServices as){
		
		this.as = as;
	}
	
	  @Override
	  public void onAuthenticationFailure(HttpServletRequest request,HttpServletResponse response, AuthenticationException exception)

	  throws IOException, ServletException {
		  
		  System.out.println("onAuthenticationFaulure");

	  if (exception instanceof UsernameNotFoundException&& exception.getAuthentication() instanceof OpenIDAuthenticationToken) {

	  OpenIDAuthenticationToken token = (OpenIDAuthenticationToken) exception.getAuthentication();

	    if (OpenIDAuthenticationStatus.SUCCESS.equals(token.getStatus())) {

	    // getting attributes passed by google/openID provider

	    	String allUrl = token.getIdentityUrl();
	    	String idurl = allUrl.split("=")[1]; 
	    	
	    final List<OpenIDAttribute> attrList = token.getAttributes();
	    
//	    for(OpenIDAttribute oa : attrList){
//	    	
//	    	System.out.println(oa.getName());
//	    }

	    String email = attrList.get(0).getValues().get(0);
	    System.out.println(email);

	  String username = (String) token.getPrincipal();
	  
	  Person person = new Person(null, null, email, idurl, null, null, null);
	  
	  as.createPerson(person);

	  //provide implementation to create user from information passed from 

	  //openID provider and save this user in database

	  //then redirect to redirectURL.

	    DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	    redirectStrategy.sendRedirect(request, response, "/home");

	   } else {

	  super.onAuthenticationFailure(request, response, exception);

	  }

	} 
	  }
}