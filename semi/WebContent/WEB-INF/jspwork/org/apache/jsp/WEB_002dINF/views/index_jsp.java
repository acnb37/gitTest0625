/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.66
 * Generated at: 2021-06-16 06:10:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("\r\n");
      out.write("<!-- \r\n");
      out.write("	WebContent 폴더 : 프로젝트의 웹 배포 시 최상위 폴더(ContextPath,root,contextRoot)\r\n");
      out.write("					보통 주소상에서 프로젝트 명으로 나타남\r\n");
      out.write("					http://localhost:8080/semi/이 주소에서\r\n");
      out.write("					/semi를 나타냄\r\n");
      out.write("					\r\n");
      out.write("					WebContent 하위에 존재하는 폴더/파일은 주소를 이용한 직접 요청이 가능하지만\r\n");
      out.write("					WEB-INF폴더는 직접 요청이 불가능하다.\r\n");
      out.write("					\r\n");
      out.write("	WEB-INF 폴더 : 외부(클라이언트)로 부터 직접적인 파일 접근 요청을 차단하는 폴더\r\n");
      out.write("				(파일 보호-> 보안성 상승)\r\n");
      out.write("				Servlet을 이용한 간접 접근만 가능함.\r\n");
      out.write("\r\n");
      out.write(" -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("  <style>\r\n");
      out.write("      .bg-image-full {\r\n");
      out.write("        background: no-repeat center center scroll;\r\n");
      out.write("        -webkit-background-size: cover;\r\n");
      out.write("        -moz-background-size: cover;\r\n");
      out.write("        background-size: cover;\r\n");
      out.write("        -o-background-size: cover;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      div.bg-image-full{\r\n");
      out.write("        height: 300px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("      }\r\n");
      out.write("\r\n");
      out.write("      @font-face { font-family: 'GmarketSansBold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff'); font-weight: normal; font-style: normal; }\r\n");
      out.write("      div.bg-image-full>h1{\r\n");
      out.write("        color : white;\r\n");
      out.write("        position: relative;\r\n");
      out.write("        top : 75px;\r\n");
      out.write("        font-size: 3em;\r\n");
      out.write("        font-family: 'GmarketSansBold';\r\n");
      out.write("        text-shadow: -2px 0 black, 0 2px black, 2px 0 black, 0 -2px black;\r\n");
      out.write("      }\r\n");
      out.write("  </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<!-- header.jsp 동적 include -->\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/header.jsp", out, false);
      out.write("\r\n");
      out.write("	<!-- 메인 화면 이미지 -->\r\n");
      out.write("	<div class=\"py-5 bg-image-full\" style=\"background-image: url('https://iei.or.kr/resources/images/intro/intro_bg.jpg');\">\r\n");
      out.write("	    <h1>Servlet/JSP를 이용한<br>Semi Project</h1>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	<!-- 내용 작성 부분 -->\r\n");
      out.write("	<div class=\"py-5\">\r\n");
      out.write("	  <div class=\"container\">\r\n");
      out.write("	    <h1>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.loginMember.memberName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h1>\r\n");
      out.write("	    <p class=\"lead\">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>\r\n");
      out.write("	    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, suscipit, rerum quos facilis repellat architecto commodi officia atque nemo facere eum non illo voluptatem quae delectus odit vel itaque amet.</p>\r\n");
      out.write("	  </div>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	<div class=\"py-5\">\r\n");
      out.write("	  <div class=\"container\">\r\n");
      out.write("	    <h1>Section Heading</h1>\r\n");
      out.write("	    <p class=\"lead\">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>\r\n");
      out.write("	    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, suscipit, rerum quos facilis repellat architecto commodi officia atque nemo facere eum non illo voluptatem quae delectus odit vel itaque amet.</p>\r\n");
      out.write("	  </div>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/footer.jsp", out, false);
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
