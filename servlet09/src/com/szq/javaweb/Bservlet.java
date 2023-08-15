package com.szq.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Bservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Object settime = req.getAttribute("settime");
        out.println("系统当前时间："+settime);

        //获取客户端的IP地址
        String remoteAddr = req.getRemoteAddr();
        System.out.println(remoteAddr);
    }
}
