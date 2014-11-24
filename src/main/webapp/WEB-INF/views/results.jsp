<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profiles Form</title>
</head>
<body>
<h2>Submitted Profile Information</h2>
   <table>
    <tr>
        <td>ID</td>
        <td>${id}</td>
    </tr>
    <tr>
        <td>FirstName</td>
        <td>${firstname}</td>
    </tr>
    <tr>
        <td>LastName</td>
        <td>${lastname}</td>
    </tr>
      <tr>
        <td>Organization</td>
        <td>${organization}</td>
    </tr>
    
    <tr>
        <td>Email</td>
        <td>${email}</td>
    </tr>
       <tr>
        <td>Address</td>
        <td>${address}</td>
    </tr>
    
    <tr>
        <td>About Myself</td>
        <td>${aboutMyself}</td>
    </tr>
  
</table> 
</body>
</html>