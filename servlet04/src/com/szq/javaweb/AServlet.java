package com.szq.javaweb;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        ServletContext servletContext = this.getServletContext();
        out.print("ServletContext的对象是"+servletContext);
        out.print("<br>");

        //获取上下文的初始化参数
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements())
        {
            String element = initParameterNames.nextElement();
            out.print(element+"="+servletContext.getInitParameter(element));
            out.print("<br>");
        }

        //获取context path （获取应用上下文的根）
        String contextPath = servletContext.getContextPath();
        out.print(contextPath);
        out.print("<br>");

        //获取文件的绝对路径
        String realPath = servletContext.getRealPath("/index.html");//从web根开始web/index.html
        out.print(realPath);
        out.print("<br>");

        String realPath1 = servletContext.getRealPath("/common/common.html");
        out.print(realPath1);

        out.print("<br>");
        //先ServletContext应用域存取数据
        User user=new User("jack","123");
        //存数据
        servletContext.setAttribute("userobj",user);
        //取数据
        Object userobj = servletContext.getAttribute("userobj");
        out.print(userobj);

    }
}
