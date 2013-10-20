    

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/mvote">
select *  from elections
</sql:query>

<html>
  <head>
    <title>DB Test</title>
  </head>
  <body>

Controller message : ${message}


  <h2>Results</h2>
  
<c:forEach var="row" items="${rs.rows}">
    Foo ${row.ele_title}<br/>
    Bar ${row.ele_desc}<br/>
</c:forEach>

  </body>
</html>
