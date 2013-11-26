package org.apache.jsp.WEB_002dINF.jsp.editors;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class savegameEditor_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_form_form_method_id_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_varStatus_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_form_form_method_id_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_forEach_varStatus_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_form_form_method_id_action.release();
    _jspx_tagPool_c_forEach_varStatus_var_items.release();
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
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("  $(\"#savegameForm\").on(\"submit\", function(event) {\r\n");
      out.write("    event.preventDefault();\r\n");
      out.write("    $.ajax({\r\n");
      out.write("      type: \"POST\",\r\n");
      out.write("      url: $(\"#savegameForm\").attr(\"action\"),\r\n");
      out.write("      data: $(\"#savegameForm\").serialize(),\r\n");
      out.write("      success: function(data, textStatus, jqXHR) {\r\n");
      out.write("        $(\"#tab-savegameEditor\").html(data);\r\n");
      out.write("      }\r\n");
      out.write("    });\r\n");
      out.write("  });\r\n");
      out.write("</script>\r\n");
      out.write("<div>\r\n");
      out.write("  <div>Special Thanks to David S. Raley for creating WCSAV, which was used to identify savegame file offsets.</div>\r\n");
      out.write("  ");
      if (_jspx_meth_form_form_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_form_form_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:form
    org.springframework.web.servlet.tags.form.FormTag _jspx_th_form_form_0 = (org.springframework.web.servlet.tags.form.FormTag) _jspx_tagPool_form_form_method_id_action.get(org.springframework.web.servlet.tags.form.FormTag.class);
    _jspx_th_form_form_0.setPageContext(_jspx_page_context);
    _jspx_th_form_form_0.setParent(null);
    _jspx_th_form_form_0.setAction("savegame.html");
    _jspx_th_form_form_0.setMethod("POST");
    _jspx_th_form_form_0.setId("savegameForm");
    int[] _jspx_push_body_count_form_form_0 = new int[] { 0 };
    try {
      int _jspx_eval_form_form_0 = _jspx_th_form_form_0.doStartTag();
      if (_jspx_eval_form_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          if (_jspx_meth_c_forEach_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_form_form_0, _jspx_page_context, _jspx_push_body_count_form_form_0))
            return true;
          out.write("\r\n");
          out.write("    <button type=\"submit\">Save</button>&nbsp;<button type=\"reset\">Reset</button>\r\n");
          out.write("  ");
          int evalDoAfterBody = _jspx_th_form_form_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_form_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_form_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_form_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_form_0.doFinally();
      _jspx_tagPool_form_form_method_id_action.reuse(_jspx_th_form_form_0);
    }
    return false;
  }

  private boolean _jspx_meth_c_forEach_0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_form_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_form_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_varStatus_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_form_0);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${command.savegames}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("savegame");
    _jspx_th_c_forEach_0.setVarStatus("savegameStatus");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("      <fieldset>\r\n");
          out.write("        <legend>Savegame ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${savegameStatus.count}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</legend>\r\n");
          out.write("        Name: <input name=\"savegames[");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${savegameStatus.index}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("].name\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${savegame.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"></input><br/>\r\n");
          out.write("        Campaign: <input name=\"savegames[");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${savegameStatus.index}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("].campaign\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${savegame.campaign}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"></input><br/>\r\n");
          out.write("        Series: <input name=\"savegames[");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${savegameStatus.index}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("].series\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${savegame.series}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"></input><br/>\r\n");
          out.write("        Mission: <input name=\"savegames[");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${savegameStatus.index}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("].mission\" value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${savegame.mission}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"></input><br/>\r\n");
          out.write("      </fieldset>\r\n");
          out.write("    ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_varStatus_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
