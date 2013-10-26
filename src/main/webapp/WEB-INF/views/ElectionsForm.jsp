<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Elections Home</title>
<style>
body {
 font-size: 15px;
 color: teal;
 font-family: verdana;
}

td {
 font-size: 15px;
 color: black;
 width: 100px;
 height: 22px;
 text-align: center;
}
.heading {
 font-size: 18px;
 color: white;
 font: bold;
 background-color: orange;
 border: thick;
}
</style>
</head>

<body>
  <div>  
   <form:form method="get" action="create" modelAttribute="election">  
    <table>  
     <tr>  
      <td>Election Title :</td>  
      <td><form:input path="electTitle" /></td>  
     </tr>  
     <tr>  
      <td>Election Start Date :</td>  
      <td><form:input path="electStartDate" /></td>  
     </tr>
     <tr>  
      <td>Election End Date :</td>  
      <td><form:input path="electEndDate" /></td>  
     </tr>   
     <tr>  
      <td>Election Description :</td>  
      <td><form:input path="electDescription" /></td>  
     </tr>   
     <tr>  
      <td> </td>  
      <td><input type="submit" value="Save" /></td>  
     </tr>  
     <tr>  
        
      <td colspan="2"><a href="elections">Click Here to See User List</a></td>  
     </tr>  
    </table>  
   </form:form>  
  </div>  

</body>
</html>
