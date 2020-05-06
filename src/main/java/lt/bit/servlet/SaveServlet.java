package lt.bit.servlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.data.Database;
import lt.bit.data.Person;

@WebServlet(name = "SaveServlet", urlPatterns = {"/save"})
public class SaveServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String idS = request.getParameter("personID");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String salaryS = request.getParameter("salary");
        String birthDateS = request.getParameter("bdate");
        Connection conn = (Connection)request.getAttribute("conn");
        Person p = null;
        Integer id = null;
        Date birthDate = null;
        BigDecimal salary = null;

        if (idS != null) {
            try {
                id = new Integer(idS);
                p = Database.getPerson(conn, id);

                if (p == null) {
                    response.sendRedirect("index.jsp");
                }
            } catch (Exception e) {

            }
        } else {
            p = new Person();
        }
        p.setFirstName(firstName);
        p.setLastName(lastName);

        try {
            if (birthDateS != null) {
                birthDate = sdf.parse(birthDateS);
                p.setBirthDate(birthDate);
            }
            if (salaryS != null) {
                salary = new BigDecimal(salaryS);
                p.setSalary(salary);
            }

        } catch (Exception e) {

        }

        if (idS == null) {
            Database.addPerson(conn, p);
        } else {
            Database.updatePerson(conn, id, p);
        }

        response.sendRedirect("index.jsp");

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
