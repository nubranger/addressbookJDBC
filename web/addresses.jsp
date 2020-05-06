<%@ page import="lt.bit.data.Address" %>
<%@ page import="lt.bit.data.Database" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="lt.bit.data.Person" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Addresses</title>
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

    for (Address a : Database.getPersonsAddresses(conn, id)) {
%>
ID: <%=a.getId()%> Adresas: <%=a.getAddress()%> Miestas: <%=a.getCity()%> Pasto kodas: <%=a.getPostalCode()%>
<a href="editAddress.jsp?addressID=<%=a.getId()%>&personID=<%=id%>">Edit</a>
<a href="removeAddress?addressID=<%=a.getId()%>&personID=<%=id%>">Remove</a>
<br>
<hr>
<%
    }
%>
<a href="editAddress.jsp?personID=<%=id%>">Add new address</a>
<a href="index.jsp">Back</a>

</body>
</html>
