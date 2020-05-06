package lt.bit.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebFilter(filterName = "ConnectionFilter", urlPatterns = "/*")
public class ConnectionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC", "root", "root");
        } catch (SQLException ex) {
            throw new ServletException("Failed to connect to DB", ex);
        }
        req.setAttribute("conn", conn);
        chain.doFilter(req, resp);
        req.removeAttribute("conn");
        try {
            conn.close();
        } catch (SQLException ex) {
            //ignore
        }
    }

    public void init(FilterConfig config) throws ServletException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("failed to load driver");

        }
    }

}
