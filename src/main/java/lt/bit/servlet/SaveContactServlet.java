package lt.bit.servlet;

import lt.bit.data.Contact;
import lt.bit.data.Database;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "SaveContactServlet", urlPatterns = {"/saveContact"})
public class SaveContactServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String idS = request.getParameter("personID");
        String idContactS = request.getParameter("contactID");
        String contact = request.getParameter("contact");
        String contactType = request.getParameter("type");
        Connection conn = (Connection) request.getAttribute("conn");
        Contact c = null;
        Integer id = null;
        Integer idContact = null;

        try {
            id = new Integer(idS);
        } catch (Exception e) {

        }
        if (idContactS != null) {
            try {
                idContact = new Integer(idContactS);
                c = Database.getContact(conn, idContact);
                if (c == null) {
                    response.sendRedirect("contacts.jsp?personID=" + id);
                }
            } catch (Exception e) {

            }
        } else {
            c = new Contact();
        }
        c.setContact(contact);
        c.setType(contactType);


        if (idContactS == null) {
            Database.addContact(conn, id, c);
        } else {
            Database.updateContact(conn, idContact, c);
        }

        response.sendRedirect("contacts.jsp?personID=" + id);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
