<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>My ToDo List</title>
    </head>
    <body>
    <form action="updatelist" method="POST">
    <font color="navy" style="font-family:Sans-Serif;">
        <h2>Your To Do List</h2>
        </font>
        <c:forEach var="tasks" items="${actionitem}">
            <c:out value="${tasks}" />
            <input type="checkbox" id="updateitem" name="item status" >
            <label for="updateitem">${tasks}</label>
         </c:forEach>
         <br />
         <a href = "index.jsp">Logout</a>
     </form>
     </body>
  </html>


