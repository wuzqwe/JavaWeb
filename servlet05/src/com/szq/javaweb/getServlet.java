package com.szq.javaweb;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class getServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("    <!DOCTYPE html>");
        out.println("    <html lang='en'>");
        out.println("    <head>");
        out.println("        <meta charset='UTF-8'>");
        out.println("        <title>Title</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("      <h1>from get servlet</h1>");
        out.println("    </body>");
        out.println("    </html>");
    }
}
