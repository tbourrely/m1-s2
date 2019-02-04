package tp3sa.question1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SessionEditor extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        

        String username = request.getParameter("username");
        String city = request.getParameter("city");
        Integer age = Integer.parseInt(request.getParameter("age"));

        session.setAttribute("username", username);
        session.setAttribute("city", city);
        session.setAttribute("age", age);

        response.sendRedirect(request.getContextPath() + "/details.jsp");
    }
}