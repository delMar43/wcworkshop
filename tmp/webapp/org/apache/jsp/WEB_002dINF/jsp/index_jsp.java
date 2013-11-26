package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <title>WC Workshop</title>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styles/base/jquery-ui.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styles/default/style.css\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath() );
      out.write("/styles/wcworkshop.css\" />\r\n");
      out.write("    <script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/jquery-2.0.3.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/jquery-ui-1.10.3.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/jquery.layout-latest.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.print(request.getContextPath() );
      out.write("/scripts/jquery.jstree.js\"></script>\r\n");
      out.write("    <script>\r\n");
      out.write("      var editorTabs;\r\n");
      out.write("      var openTabs = {};\r\n");
      out.write("      \r\n");
      out.write("      $(function() {\r\n");
      out.write("        var layout = $(\"body\").layout({ applyDefaultStyles: true });\r\n");
      out.write("        layout.sizePane(\"west\", 280);\r\n");
      out.write("        $(\"#projectViewTabs\").tabs();\r\n");
      out.write("        editorTabs = $(\"#editorTabs\").tabs();\r\n");
      out.write("        \r\n");
      out.write("        $(\".ui-tabs-nav\").sortable();\r\n");
      out.write("        \r\n");
      out.write("        $.ajax({\r\n");
      out.write("          url: \"");
      out.print(request.getContextPath());
      out.write("/campaignContentsTree.html\",\r\n");
      out.write("          success: function(data, textStatus, jqXHR) {\r\n");
      out.write("            $(\"#tab-campaignContents\").html(data);\r\n");
      out.write("            /*\r\n");
      out.write("            $(\"#campaignTree\").jstree({\r\n");
      out.write("              \"plugins\" : [\"themes\",\"html_data\",\"ui\",\"crrm\",\"hotkeys\"],\r\n");
      out.write("              \"types\": {\r\n");
      out.write("                \"default\":{\r\n");
      out.write("                  \"max_children\":-2,\r\n");
      out.write("                  \"max_depth\":-2,\r\n");
      out.write("                  \"valid_children\":\"all\"\r\n");
      out.write("                }\r\n");
      out.write("              }\r\n");
      out.write("            });\r\n");
      out.write("            */\r\n");
      out.write("          }\r\n");
      out.write("        });\r\n");
      out.write("        \r\n");
      out.write("        $.ajax({\r\n");
      out.write("          url: \"");
      out.print(request.getContextPath());
      out.write("/savegameEditor.html\",\r\n");
      out.write("          success: function(data, textStatus, jqXHR) {\r\n");
      out.write("            $(\"#tab-savegameEditor\").html(data);\r\n");
      out.write("          }\r\n");
      out.write("        });\r\n");
      out.write("        \r\n");
      out.write("        // close icon: removing the tab on click\r\n");
      out.write("        editorTabs.delegate( \"span.ui-icon-close\", \"click\", function() {\r\n");
      out.write("          var li = $( this ).closest( \"li\" );\r\n");
      out.write("          var panelId = li.remove().attr( \"aria-controls\" );\r\n");
      out.write("          $( \"#\" + panelId ).remove();\r\n");
      out.write("          editorTabs.tabs( \"refresh\" );\r\n");
      out.write("          delete openTabs[li.attr(\"id\")];\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("      });\r\n");
      out.write("\r\n");
      out.write("      var addTab = function(label, href) {\r\n");
      out.write("        var ul = editorTabs.find(\".ui-tabs-nav\");\r\n");
      out.write("        $(\"<li id='\" + label + \"'><a href='\" + href + \"'>\" + label+ \"</a> <span class='ui-icon ui-icon-close' role='presentation'>Close</span></li>\").appendTo(ul);\r\n");
      out.write("        editorTabs.tabs(\"refresh\");\r\n");
      out.write("        openTabs[label] = true;\r\n");
      out.write("      }\r\n");
      out.write("      \r\n");
      out.write("      var openSeriesEditor = function(seriesIndex) {\r\n");
      out.write("    \tvar label = \"Series \" + (seriesIndex+1);\r\n");
      out.write("        if (label in openTabs) {\r\n");
      out.write("          return;\r\n");
      out.write("        }\r\n");
      out.write("        addTab(label, \"");
      out.print(request.getContextPath());
      out.write("/seriesEditor.html?seriesIndex=\" + seriesIndex);\r\n");
      out.write("      };\r\n");
      out.write("      \r\n");
      out.write("      var openMissionEditor = function(seriesIndex, missionIndex) {\r\n");
      out.write("    \tvar label = \"S\" + (seriesIndex+1) + \"M\" + (missionIndex+1);\r\n");
      out.write("    \tif (label in openTabs) {\r\n");
      out.write("          return;\r\n");
      out.write("    \t}\r\n");
      out.write("    \taddTab(label, \"");
      out.print(request.getContextPath());
      out.write("/missionEditor.html?seriesIndex=\" + seriesIndex + \"&missionIndex=\" + missionIndex);\r\n");
      out.write("      };\r\n");
      out.write("      \r\n");
      out.write("      var openCutsceneEditor = function(seriesIndex, missionIndex, cutsceneIndex) {\r\n");
      out.write("    \tvar label = \"S\" + (seriesIndex+1) + \"M\" + (missionIndex+1) + \"C\" + cutsceneIndex;\r\n");
      out.write("    \tif (label in openTabs) {\r\n");
      out.write("          return;\r\n");
      out.write("    \t}\r\n");
      out.write("    \taddTab(label, \"");
      out.print(request.getContextPath());
      out.write("/cutsceneEditor.html?seriesIndex=\" + seriesIndex + \"&missionIndex=\" + missionIndex + \"&cutsceneIndex=\" + cutsceneIndex);\r\n");
      out.write("      }\r\n");
      out.write("    </script>\r\n");
      out.write("  </head>\r\n");
      out.write("  \r\n");
      out.write("  <body>\r\n");
      out.write("    <div class=\"ui-layout-north\">WC Workshop</div>\r\n");
      out.write("    <div class=\"ui-layout-center\">\r\n");
      out.write("      <div id=\"editorTabs\">\r\n");
      out.write("        <ul>\r\n");
      out.write("          <li><a href=\"#tab-savegameEditor\">Savegame Editor</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <div id=\"tab-savegameEditor\">\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"ui-layout-west\">\r\n");
      out.write("      <div id=\"projectViewTabs\">\r\n");
      out.write("        <ul>\r\n");
      out.write("          <!-- li><a href=\"#tab-allContents\">All</a></li -->\r\n");
      out.write("          <li><a href=\"#tab-campaignContents\">Campaign</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <!-- div id=\"tab-allContents\"></div -->\r\n");
      out.write("        <div id=\"tab-campaignContents\"></div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </body>\r\n");
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
