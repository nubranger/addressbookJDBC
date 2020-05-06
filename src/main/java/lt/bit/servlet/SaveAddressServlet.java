package lt.bit.servlet;

import lt.bit.data.Address;
import lt.bit.data.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "SaveAddressServlet", urlPatterns = {"/saveAddress"})
public class SaveAddressServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String idS = request.getParameter("personID");
        String idAddressS = request.getParameter("addressID");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalCode");
        Connection conn = (Connection) request.getAttribute("conn");
        Address a = null;
        Integer id = null;
        Integer idAddress = null;

        try {
            id = new Integer(idS);
        } catch (Exception e) {

        }
        if (idAddressS != null) {
            try {
                idAddress = new Integer(idAddressS);
                a = Database.getAddress(conn, idAddress);
                if (a == null) {
                    response.sendRedirect("addresses.jsp?personID=" + id);
                }
            } catch (Exception e) {

            }
        } else {
            a = new Address();
        }
        a.setAddress(address);
        a.setCity(city);
        a.setPostalCode(postalCode);

        if (idAddressS == null) {
            Database.addAddress(conn, id, a);
        } else {
            Database.updateAddress(conn, idAddress, a);
        }

        response.sendRedirect("addresses.jsp?personID=" + id);
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
