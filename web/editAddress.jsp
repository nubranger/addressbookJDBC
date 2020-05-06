<%@page import="lt.bit.data.Address"%>
<%@page import="lt.bit.data.Database"%>
<%@ page import="java.sql.Connection" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <%
        String idS = request.getParameter("addressID");
        String personID = request.getParameter("personID");
        Connection conn = (Connection) request.getAttribute("conn");
        Integer id = null;
        Address a = null;

        try {
            id = new Integer(idS);
        } catch (Exception e) {

        }
        if (id != null) {
            a = Database.getAddress(conn,id);
        }
    %>
    <form method="get" action="saveAddress">
        <%
            if (a != null) {
        %>
        <input type="hidden" value="<%=a.getId()%>" name="addressID">
        <%
            }
        %>
        <input type="hidden" value="<%=personID%>" name="personID">
        Address: <input name="address" value="<%=(a!=null)?a.getAddress():""%>">
        City: <input name="city" value="<%=(a!=null)?a.getCity():""%>">
        Postal code: <input name="postalCode" value="<%=(a!=null)?a.getPostalCode():""%>">
        <input type="submit" value="Save">
    </form>

    <a href = "addresses.jsp?personID=<%=personID%>">Cancel</a>

    </body>
</html>
