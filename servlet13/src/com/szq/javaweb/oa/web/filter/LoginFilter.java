package com.szq.javaweb.oa.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request1, ServletResponse response1, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) request1;
        HttpServletResponse response=(HttpServletResponse) response1;

        String servletPath=request.getServletPath();
        HttpSession session = request.getSession(false);
        if("/index.jsp".equals(servletPath)||"/welcome".equals(servletPath)
                ||"/user/login".equals(servletPath)||"user/exit".equals(servletPath)
                ||(session!=null&&session.getAttribute("user")!=null))
        {
         chain.doFilter(request,response);
        }
        else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }

}
