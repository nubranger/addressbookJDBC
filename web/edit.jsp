<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="lt.bit.data.Person" %>
<%@ page import="lt.bit.data.Database" %>
<%@ page import="java.sql.Connection" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

<%
    Connection conn = (Connection) request.getAttribute("conn");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String idS = request.getParameter("personID");
    Integer id = null;
    Person p = null;

    try {
        id = new Integer(idS);
    } catch (Exception e) {

    }
    if (id != null) {
        p = Database.getPerson(conn, id);
    }
%>

<form method="POST" action="save" autocomplete="off">
    <%
        if (p != null) {
    %>
    <input type ="hidden" value="<%=p.getId()%>" name="personID" >
    <%
        }
    %>
    First name: <input name="fname" value="<%=(p != null) ? p.getFirstName() : ""%>"><br>
    Last name: <input name="lname" value="<%=(p != null) ? p.getLastName() : ""%>"><br>
    Birth date: <input type="date" name="bdate" value="<%=(p != null) ? ((p.getBirthDate() != null) ? sdf.format(p.getBirthDate()) : "null") : ""%>"><br>
    Salary: <input name="salary" value="<%=(p != null) ? p.getSalary() : ""%>"><br>
    <input type="submit" value="<%=(p != null) ? "Edit" : "Add"%>">
    <input type="button" onclick="window.location.href = 'index.jsp'" value="Cancel">
</form>
</body>
</html>
