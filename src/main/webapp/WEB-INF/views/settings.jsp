<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<!-- saved from url=(0026)https://twitter.com/signup -->
<html lang="en" data-scribe-reduced-action-queue="true"><!--<![endif]--><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta charset="utf-8">
    
    <link rel="stylesheet" href="https://abs.twimg.com/a/1417027384/css/t1/twitter_core.bundle.css">
    <link rel="stylesheet" href="https://abs.twimg.com/a/1417027384/css/t1/twitter_logged_out.bundle.css">

	
      <title>Settings</title>
      
  </head>
  <body class="three-col logged-out phx-signup" data-fouc-class-names="swift-loading" dir="ltr">

    <div id="doc" class="route-signup">
        

        <div id="page-outer">
          <div id="page-container" class="AppContent wrapper wrapper-signup">
 
<div class="page-canvas">
  <div class="signup-wrapper">

          <h1>
            Change Your Profile.
          </h1>

    <form id="phx-signup-form" method="POST" action="/lab3/update" class="t1-form ">

  <div class="textbox">

      <div class="prompt name">
  <label for="first-name" class="t1-label field-name">First name</label>
  <div class="field" data-fieldname="name">
    
    <input id="firstname" type="text" autocomplete="off"  name="firstname" maxlength="20" aria-required="true" placeholder = "${firstname }">
  </div>
</div>

<div class="prompt name">
  <label for="last-name" class="t1-label field-name">Last name</label>
  <div class="field" data-fieldname="name">
    
    <input id="lastname" type="text" autocomplete="off" name="lastname" maxlength="20" aria-required="true" placeholder = "${lastname }">
  </div>
</div>

      <div class="prompt Street">
    <label for="password" class="t1-label field-name">
      
      Street
    </label>
  <div class="field" data-fieldname="street">
    
    <input id="street" type="text" autocomplete="off" name="street" aria-required="true" placeholder = "${street }">
  </div>
    
</div>

<div>
City
  <div class="field" data-fieldname="city">
    
    <input id="city" type="text" name="city" autocomplete="off" aria-required="true" placeholder = "${city }">
  </div>
    
</div>

<div>
Country
  <div class="field" data-fieldname="country">
    
    <input id="country" type="text" autocomplete="off" name="country" aria-required="true" placeholder = "${country }">
  </div>
    
</div>
<div>
Zipcode
  <div class="field" data-fieldname="zipcode">
    
    <input id="zipcode" type="text" autocomplete="off" name="zipcode" aria-required="true" placeholder = "${zipcode }">
  </div>
</div>

<div>
Organization
  <div class="field" data-fieldname="zipcode">
    <select name="org" size="1">
    <option>${org}</option>
    <c:forEach var="e" items="${orgs}">
       <option>${e.getName()}</option>
    </c:forEach>
    </select>
  </div>
</div>

  </div>
<br>
  <div class="doit">

    <div class="sign-up-box">
      <button class="btn btn-lg btn-primary btn-block" type="submit">Save Changes</button>&nbsp;&nbsp;&nbsp;<a href = "/lab3/home">Back</a>
       
    </div>

  </div>
</form>

  </div>
</div>
          </div>
        </div>
      
    </div>
   
</body></html>