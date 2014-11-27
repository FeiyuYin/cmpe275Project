<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
</head>
<body>
<form action="/lab3/signup" id="signupform" method="post">
<center>
<table border="0" cellpadding="5" cellspacing="0" width="600">
<tr>
<td><b>First, Last Name:</b></td>
<td>
<input id="firstname" name="firstname" type="text" maxlength="60" style="width:146px; border:1px solid #999999" />
<input id="lastname" name="lastname" type="text" maxlength="60" style="width:146px; border:1px solid #999999" />
</td>
</tr>
<tr>
<td><b>Email address:</b></td>
<td><input id="email" name="email" type="text" maxlength="60" style="width:300px; border:1px solid #999999" />${error}</td>
</tr>
<tr>
<td><b>Password:</b></td>
<td><input id="password" name="password" type="text" maxlength="60" style="width:300px; border:1px solid #999999" /></td>
</tr>
<tr>
<td><b>Street:</b></td>
<td><input id="street" name="street" type="text" maxlength="100" style="width:300px; border:1px solid #999999" /></td>
</tr>
<tr>
<td><b>City:</b></td>
<td><input id="city" name="city" type="text" maxlength="100" style="width:300px; border:1px solid #999999" /></td>
</tr>
<tr>
<td><b>Country:</b></td>
<td><input id="country" name="country" type="text" maxlength="100" style="width:300px; border:1px solid #999999" /></td>
</tr>
<tr>
<td><b>Zipcode:</b></td>
<td><input id="zipcode" name="zipcode" type="text" maxlength="100" style="width:300px; border:1px solid #999999" /></td>
</tr>
<tr>
<td><b>About Myself:</b></td>
<td><input id="description" name="aboutMyself" type="text" maxlength="300" style="width:300px; border:1px solid #999999" /></td>
</tr>

<tr>
<td colspan="2" align="center">
<br />
<table border="0" cellpadding="0" cellspacing="0">

</table>
<br/>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;

<input id="Create" name="Create" type="submit" value="Create" />
</td>
</tr>
</table>
</center>
<br/>
</form>
</body>
</html>