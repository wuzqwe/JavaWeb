package com.szq.javaweb;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class ConfigTestServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

      ServletConfig config = getServletConfig();

      String servletName=config.getServletName();
        out.print("<servlet-name>"+servletName+"</servlet-name>");
        out.print("<br>");



        Enumeration<String> initParameterNames = config.getInitParameterNames();

       while (initParameterNames.hasMoreElements())
       {
            String element = initParameterNames.nextElement();
           String initParameter = config.getInitParameter(element);
           out.print(element+"="+initParameter);
            out.print("<br>");
       }

       /* String parameter = config.getInitParameter("driver");
        out.print(parameter);
        out.print("<br>");*/
        String initParameter = this.getInitParameter("driver");
        out.print(initParameter);
    }
}
