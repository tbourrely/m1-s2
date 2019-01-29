package helloworld;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        Cookie cookie = getCookie(request.getCookies(), "name");
        String name = cookie != null ? cookie.getValue() : "";

        out.println("<!DOCTYPE html/><html><head></head><body>");
        out.println("<form method='POST' action='/tp2/showname'>");
        out.println("<input type='text' name='name' placeholder='name'value='" + name + "'>");
        out.println("<input type='submit'>");
        out.println("</form>");
        out.println("</body></html>");
        out.flush();
        out.close();
    }

    public static Cookie getCookie(Cookie[] cookies, String cookieName) {
        for(int i = 0; i < cookies.length; i++) {
            if(cookieName.equals(cookies[i].getName()))
                return cookies[i];
        }

        return null;
    }
}
