<%@page import="lt.bit.data.Database"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="lt.bit.data.Contact" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <%
        String idS = request.getParameter("contactID");
        String personID = request.getParameter("personID");
        Connection conn = (Connection) request.getAttribute("conn");
        Integer id = null;
        Contact c = null;

        try {
            id = new Integer(idS);
        } catch (Exception e) {

        }
        if (id != null) {
            c = Database.getContact(conn,id);
        }
    %>
    <form method="get" action="saveContact">
        <%
            if (c != null) {
        %>
        <input type="hidden" value="<%=c.getId()%>" name="contactID">
        <%
            }
        %>
        <input type="hidden" value="<%=personID%>" name="personID">
        Contact: <input name="contact" value="<%=(c!=null)?c.getContact():""%>">
        Contact type: <input name="type" value="<%=(c!=null)?c.getType():""%>">
        <input type="submit" value="Save">
    </form>

    <a href = "contacts.jsp?personID=<%=personID%>">Cancel</a>

    </body>
</html>
