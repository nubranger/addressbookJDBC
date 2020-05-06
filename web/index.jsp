<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="lt.bit.data.Person" %>
<%@ page import="lt.bit.data.Database" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Address book JDBC</title>
  </head>
  <body>

  <%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Connection conn = (Connection)request.getAttribute("conn");
  %>
  <h2>Person list</h2>
  <%
    for (Person p : Database.getPersons(conn)) {
  %>

  ID: <%=p.getId()%>
  Vardas: <%=p.getFirstName()%>
  Pavarde: <%=p.getLastName()%>
  Gimimo data: <%=(p.getBirthDate() != null) ? sdf.format(p.getBirthDate()) : ""%>
  Alga: <%=p.getSalary()%>
  <a href="edit.jsp?personID=<%=p.getId()%>">Edit</a>
  <a href="remove?personID=<%=p.getId()%>">Remove</a> <br>
  <a href="addresses.jsp?personID=<%=p.getId()%>">Addresses</a>
  <a href="contacts.jsp?personID=<%=p.getId()%>">Contacts</a>
  <hr>
  <%
    }
  %>
  <a href="edit.jsp">New</a>

  </body>
</html>
