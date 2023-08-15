package com.szq.javaweb;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();


        ServletContext servletContext = this.getServletContext();
        //获取ServletContext对象
        //记录日志记录到logs文件中
        servletContext.log("你好大憨憨");


        //取共享数据中
        Object userobj = servletContext.getAttribute("userobj");
        out.print(userobj);
    }
}
