package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class page1_jsp extends org.apache.jasper.runtime.HttpJspBase
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

 
    String detailsURL = response.encodeURL("/tp3/details.jsp");

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<a href=\"");
      out.print( detailsURL );
      out.write("\">Details</a>\n");
      out.write("<br>\n");
      out.write("\n");
      out.write("\n");
      out.write("L'islandais est un cheval de selle de petite taille qui forme l'unique race chevaline originaire d'Islande. Ces animaux sont vraisemblablement les descendants directs des montures amenÃ©es en bateau par les Vikings lors de la colonisation de l'Islande. Les Islandais sont toujours restÃ©s trÃ¨s fiers de leurs chevaux qu'ils citent rÃ©guliÃ¨rement dans leurs sagas. Les importations de chevaux sont interdites sur l'Ã®le depuis le xe siÃ¨cle et de ce fait, l'islandais n'a pas subi de croisements depuis les annÃ©es 900. Il resta trÃ¨s longtemps une race exclusive Ã  l'Ã®le d'Islande et la sÃ©lection naturelle lui permit d'acquÃ©rir une grande rÃ©sistance aux conditions climatiques en se contentant d'une nourriture pauvre.\n");
      out.write("\n");
      out.write("Bien qu'il y ait une relation Ã©troite entre ces chevaux et des poneys, surtout celtiques, les Islandais ont gardÃ© le nom de Â« cheval Â» pour dÃ©signer leurs montures. Ces animaux sont caractÃ©risÃ©s par leur taille rÃ©duite, leur grande robustesse et rusticitÃ©, leurs robes trÃ¨s variÃ©es et leur particularitÃ© de possÃ©der frÃ©quemment cinq allures, soit le tÃ¶lt et l'amble en plus des trois allures habituelles du cheval. Leur utilisation est multiple puisqu'ils servent encore au gardiennage des moutons sur leur Ã®le d'origine. Des courses et des concours d'allures spÃ©ciaux leur sont totalement rÃ©servÃ©s, ils peuvent Ã©galement Ãªtre Ã©levÃ©s pour leur viande bien qu'ils soient employÃ©s comme montures de loisir.\n");
      out.write("\n");
      out.write("L'islandais ne fut exportÃ© que tardivement, au xxe siÃ¨cle. Depuis, son succÃ¨s en a fait une race reprÃ©sentÃ©e par des associations dans 19 pays, particuliÃ¨rement en Europe de l'Ouest, en Scandinavie et en AmÃ©rique du Nord. PrÃ¨s de la moitiÃ© des chevaux islandais exportÃ©s se trouvent en Allemagne.\n");
      out.write("</body>\n");
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
