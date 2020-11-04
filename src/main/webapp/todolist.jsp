<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <head>
        <title>My ToDo List</title>
    </head>
    <body>
        <p>
        <font color="navy" style="font-family:Sans-Serif;">
        <center>
        <h1>To Do List for ${username}</h1>
        </font>
        <form action="todolist" method="POST">
        <label>Enter an action item to do</label>
        <br/>
        <input type="text" name="item" />
        <br /> <br />
        <br />
        <input type="submit" name="submit" value="Add" />
        <br />
        <br />
        </p>
        <p>
        <font color="navy" style="font-family:Sans-Serif;">
        <h2>Your current list is:</h2>
        </font>

        <c:forEach var="actionitem" items="${actionitemlist}">
            <tr>
            <td><c:out value="${actionitem}" /></td>
            <br />
        </c:forEach>
        </tr>
        <br /> <br /> <br />
        </center>
        <label><h3><font color="teal">Do you want to update your list?</font></h3></label>
        <input type="submit" name = "status" value = "Update List" />
        </p>
        <a href = "index.jsp">Logout</a>
        </form>
    </body>
</html>