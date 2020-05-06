package lt.bit.servlet;

import lt.bit.data.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


@WebServlet(name = "RemoveAddressServlet", urlPatterns = {"/removeAddress"})
public class RemoveAddressServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idS = request.getParameter("personID");
        String idAddressS = request.getParameter("addressID");
        Connection conn = (Connection)request.getAttribute("conn");
        Integer id = null;
        Integer idAddress;

        try {
            id = new Integer(idS);
            idAddress = new Integer(idAddressS);
            Database.delAddress(conn, idAddress);
        } catch (Exception e) {

        }

        response.sendRedirect("addresses.jsp?personID=" + id);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
