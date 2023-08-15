package com.szq.javaweb;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class postServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
      out.print("    <!DOCTYPE html>");
      out.print("    <html lang='en'>");
      out.print("    <head>");
      out.print("        <meta charset='UTF-8'>");
      out.print("        <title>Title</title>");
      out.print("    </head>");
      out.print("    <body>");
      out.print("      <h1>from post servlet</h1>");
      out.print("    </body>");
      out.print("    </html>");




    }
}
