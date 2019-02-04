package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class details_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");



String username = (String)session.getAttribute("username");

Integer age = 0;
String city = "";

if (session.getAttribute("age") != null) {
    age = (Integer)session.getAttribute("age");
} else if(request.getParameterMap().containsKey("age")) {
    age = Integer.parseInt(request.getParameter("age"));
}

if (session.getAttribute("city") != null) {
    city = (String)session.getAttribute("city");
} else if(request.getParameterMap().containsKey("city")) {
    city = request.getParameter("city");
}



session.setAttribute("age", age);
session.setAttribute("city", city);


String editURL = response.encodeURL("/tp3/edit.jsp");
String page1URL = response.encodeURL("/tp3/page1.jsp");
String quitURL = response.encodeURL("/tp3/quitsession");


      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head></head>\n");
      out.write("    <body>\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <td>username</td>\n");
      out.write("                <td>");
      out.print( username );
      out.write("</td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>age</td>\n");
      out.write("                <td>");
      out.print( age );
      out.write("</td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>city</td>\n");
      out.write("                <td>");
      out.print( city );
      out.write("</td>\n");
      out.write("            </tr>\n");
      out.write("        </table>\n");
      out.write("        \n");
      out.write("        <a href=\"");
      out.print( editURL );
      out.write("\">Editer le profil</a><br>\n");
      out.write("        <a href=\"");
      out.print( page1URL );
      out.write("\">Page 1</a><br>\n");
      out.write("        <a href=\"");
      out.print( quitURL );
      out.write("\">Quitter la session</a>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
