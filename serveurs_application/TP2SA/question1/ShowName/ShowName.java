package showname;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowName extends HttpServlet {

    private String defaultName;

    public void init(ServletConfig c) {
        this.defaultName = c.getInitParameter("name");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name").isEmpty() ? this.defaultName : request.getParameter("name");
        PrintWriter out = response.getWriter();
        out.println("Name : " + name);
        out.println("<a href='http://localhost:8080/tp2/'>back</a>");
        out.flush();
        out.close();
    }
}
