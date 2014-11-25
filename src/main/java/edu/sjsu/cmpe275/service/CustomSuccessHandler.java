package edu.sjsu.cmpe275.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

@Override
public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication)throws ServletException, IOException {
	
	//provide implementation to set user data in session
	
	//redirecting to landing page
	System.out.println("OnAuthenticationSuccess");
		getRedirectStrategy().sendRedirect(request, response, "/lab3/home");
	
	}

	//super.onAuthenticationSuccess(request, response, authentication);

}
