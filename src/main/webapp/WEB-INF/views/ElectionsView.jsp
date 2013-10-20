<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.hashin.project.bean.ElectionsBean"%>

<html>
<head>
<title>Being Java Guys | Spring DI Hello World</title>
<style>
body {
 font-size: 20px;
 color: teal;
 font-family: Calibri;
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
 <center>
  
 <!-- 
 Election:
  <table>
  <tr>
     <td>${election.electId}</td>
     <td>${election.electTitle}</td>
     <td>${election.electStartDate}</td>
     <td>${election.electEndDate}</td>
     <td>${election.electDescription}</td>
   </tr>
   </table> 
  -->
 
  
 <b>Elections List  </b>
 
  <table border="1">
   <tr>
    <td class="heading">Election Id</td>
    <td class="heading">election Title</td>
    <td class="heading">Election Start Date</td>
    <td class="heading">Election End Date</td>
    <td class="heading">Election Description</td>
    <td class="heading">Edit</td>
    <td class="heading">Delete</td>
   </tr>
   <c:forEach var="election" items="${electionsList}">
    <tr>
     <td>${election.electId}</td>
     <td>${election.electTitle}</td>
     <td>${election.electStartDate}</td>
     <td>${election.electEndDate}</td>
     <td>${election.electDescription}</td>
     <td><a href="edit?id=${election.electId}">Edit</a></td>
     <td><a href="delete?id=${election.electId}">Delete</a></td>
    </tr>
   </c:forEach>
   <tr><td colspan="7"><a href="register">Add New Election</a></td></tr>
  </table>
 
 </center>
</body>
</html>
