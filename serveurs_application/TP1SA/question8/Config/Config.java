package config;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Config extends HttpServlet {

    private ServletConfig config;

    public void init(ServletConfig c)
    {
        this.config = c;
    }   

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        PrintWriter o = response.getWriter();
        String name = request.getParameter("defaultName");
        ServletContext context = this.config.getServletContext();
        context.setAttribute("defaultNameInContext", name);
        o.println("<!DOCTYPE html><html><head><title>Name : ");
        o.println(name);
        o.println("</title></head><body>");
        o.println("Name from post (saved in context) : " + name);
        o.println("</body></html>");
        o.flush();
        o.close();
    }
}
