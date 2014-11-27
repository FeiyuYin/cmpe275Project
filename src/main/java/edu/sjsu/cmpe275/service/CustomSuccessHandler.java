package edu.sjsu.cmpe275.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import edu.sjsu.cmpe275.model.Person;

public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	AllServices as; 
	
	public void setAllServices(AllServices as){
		
		this.as = as;
	}
@Override
public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication)throws ServletException, IOException {
	
	//provide implementation to set user data in session
	
	//redirecting to landing page
	System.out.println("OnAuthenticationSuccess");
	String email = ((Person)authentication.getPrincipal()).getEmail();
	System.out.println(email);
	HttpSession session = request.getSession();
	session.setAttribute("userSession", session);
	session.setAttribute("email", email);
	getRedirectStrategy().sendRedirect(request, response, "/home");
	
	}

	//super.onAuthenticationSuccess(request, response, authentication);

}
