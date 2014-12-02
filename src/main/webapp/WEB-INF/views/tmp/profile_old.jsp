<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function setMethod(i){

	if(i == 2){
		
		document.getElementById("method").value="DELETE";
	}
	if(i == 1){
		
		document.getElementById("method").value="POST";
	}
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>
<form:form modelAttribute="profile" id="profileform" method="post">
<center>
<table border="0" cellpadding="5" cellspacing="0" width="600">
<tr>
<td><b>ID*:</b></td>
<td><input type="hidden" id="method" name="_method" value=""/>
<input value='<c:out value="${profile.id}"></c:out>' id="id" name="id" type="text" maxlength="60" style="width:300px; border:1px solid #999999" readonly="readonly"/>
</td>

</tr>
<tr>
<td><b>First, Last Name*:</b></td>
<td>
<form:input path='firstname' id="firstname" name="firstname" type="text" maxlength="60" style="width:146px; border:1px solid #999999" />
<form:input path='lastname' id="lastname" name="lastname" type="text" maxlength="60" style="width:146px; border:1px solid #999999" />
</td>
</tr>
<tr>
<td><b>Email address*:</b></td>
<td><form:input path='email' id="email" name="email" type="text" maxlength="60" style="width:300px; border:1px solid #999999" /></td>
</tr>
<tr>
<td><b>Address*:</b></td>
<td><form:input path='address' id="address" name="address" type="text" maxlength="100" style="width:300px; border:1px solid #999999" /></td>
</tr>
<tr>
<td><b>Organization*:</b></td>
<td><form:input path='organization' id="organization" name="organization" type="text" maxlength="60" style="width:300px; border:1px solid #999999" /></td>
</tr>
<tr>
<td><b>About Myself*:</b></td>
<td><form:input path='aboutMyself' id="aboutMyself" name="aboutMyself" type="text" maxlength="300" style="width:300px; border:1px solid #999999" /></td>
</tr>
<tr>
<td colspan="2" align="center">
<br />
<table border="0" cellpadding="0" cellspacing="0">

</table>
<br/>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;

<input id="Update" name="Update" type="submit" value="Update" />
<input onclick="setMethod(2)" id="Delete" name="Delete" type = "submit" value="Delete"/>
</td>
</tr>
</table>
</center>
<br/>
</form:form>

</body>
</html>