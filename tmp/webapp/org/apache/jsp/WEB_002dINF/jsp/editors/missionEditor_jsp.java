package org.apache.jsp.WEB_002dINF.jsp.editors;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class missionEditor_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write("  <h3>Mission Parameters</h3>\r\n");
      out.write("  <table>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <th>Conversation Partners</th>\r\n");
      out.write("      <td>Left: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${campUtil.getPilot(mission.conversationPartners.leftSeat)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(", Right: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${campUtil.getPilot(mission.conversationPartners.rightSeat)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <th>Medal</th>\r\n");
      out.write("      <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${campUtil.getMedal(mission.medal)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <th>Medal Killpoints</th>\r\n");
      out.write("      <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${mission.medalKillPoints}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <th>Objective Victory Points</th>\r\n");
      out.write("      <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${objectiveVictoryPoints}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write("  <h3>Cutscenes</h3>\r\n");
      out.write("  <table>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <th>Shotglass</th>\r\n");
      out.write("      <td><a href=\"javascript:openCutsceneEditor(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${seriesIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(',');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${missionIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(", 2)\">Edit...</a></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <th>Left Chair</th>\r\n");
      out.write("      <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${campUtil.getPilot(mission.conversationPartners.leftSeat)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&nbsp;<a href=\"javascript:openCutsceneEditor(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${seriesIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(',');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${missionIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(", 3)\">Edit...</a></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <th>Right Chair</th>\r\n");
      out.write("      <td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${campUtil.getPilot(mission.conversationPartners.rightSeat)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&nbsp;<a href=\"javascript:openCutsceneEditor(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${seriesIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(',');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${missionIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(", 4)\">Edit...</a></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <th>Briefing</th>\r\n");
      out.write("      <td><a href=\"javascript:openCutsceneEditor(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${seriesIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(',');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${missionIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(", 0)\">Edit...</a></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <th>Debriefing</th>\r\n");
      out.write("      <td><a href=\"javascript:openCutsceneEditor(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${seriesIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(',');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${missionIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(", 1)\">Edit...</a></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("</div>");
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
