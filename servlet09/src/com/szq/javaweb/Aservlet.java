package com.szq.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Aservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Date nowtime=new Date();

        String contextPath1 = req.getContextPath();
        req.setAttribute("settime",nowtime);

        //将b的资源在a中显示,同一个请求域，一次请求
//        req.getRequestDispatcher("/b").forward(req,resp);
        //重定向 俩次请求
        resp.sendRedirect(contextPath1+"/b");

        //动态获取应用的根路径/项目名
        String contextPath = req.getContextPath();
        System.out.println("应用的根路径："+contextPath);

        //获取请求方式
        String method = req.getMethod();
        System.out.println("请求方式："+method);

        //获取的请求urI
        String requestURI = req.getRequestURI();
        System.out.println("获取的请求urI："+requestURI);

        //获取servletpath
        String servletPath = req.getServletPath();
        System.out.println(servletPath);

        System.out.println(resp.getContentType());
    }
}
