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
        String nameFromParams = request.getParameter("name");

        String name = this.defaultName;

       Cookie cookie = null; 

        if (!nameFromParams.isEmpty()) {
            name = nameFromParams;
            cookie = new Cookie("name", name);
        } else {
            cookie = new Cookie("name", "");
            cookie.setMaxAge(0);
        }

        if (null != cookie)
            response.addCookie(cookie);


        PrintWriter out = response.getWriter();
        out.println("Name : " + name);
        out.println("<a href='http://localhost:8080/tp2/'>back</a><br>");
        out.println("<form method='post' action='/tp2/showname'><input type='hidden' name='name' value='" + name + "'><input type='submit'></form>");
        out.println("<form method='post' action='/tp2/shownamebis'><input type='hidden' name='name' value='" + nameFromParams + "'><input type='submit' value='showNameBis'></form>");
        out.flush();
        out.close();
    }
}
