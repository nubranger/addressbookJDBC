<%@ page import="lt.bit.data.Database" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="lt.bit.data.Person" %>
<%@ page import="lt.bit.data.Contact" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
</head>
<body>

<%
    Connection conn = (Connection) request.getAttribute("conn");

    String ids = request.getParameter("personID");
    Integer id = null;
    try {
        id = new Integer(ids);
    } catch (Exception ex) {
        //ignoras
    }

    Person p = Database.getPerson(conn, id);
%>
<h2> <%=p.getFirstName()%> <%=p.getLastName()%> address list </h2>
<%

    for (Contact c : Database.getPersonsContacts(conn, id)) {
%>
ID: <%=c.getId()%> Contact: <%=c.getContact()%> Contact type: <%=c.getType()%>
<a href="editContact.jsp?contactID=<%=c.getId()%>&personID=<%=id%>">Edit</a>
<a href="removeContact?contactID=<%=c.getId()%>&personID=<%=id%>">Remove</a>
<br>
<hr>
<%
    }
%>
<a href="editContact.jsp?personID=<%=id%>">Add new address</a>
<a href="index.jsp">Back</a>

</body>
</html>
