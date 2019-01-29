package helloworld;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet {

    public String name;

    public void init(ServletConfig c)
    {
        this.name = c.getServletName();
    }   

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        out.println("Hello world to " + name);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        PrintWriter o = response.getWriter();
        String name = request.getParameter("name");
        o.println("<!DOCTYPE html><html><head><title>Name : ");
        o.println(this.name);
        o.println("</title></head><body>");
        o.println("Name from post : " + name);
        o.println("</body></html>");
        o.flush();
        o.close();
    }
}
