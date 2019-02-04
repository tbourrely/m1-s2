package tp3sa.question1;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SessionQuitter extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}