package com.szq.javaweb.oa.web.action;

import com.szq.javaweb.oa.bean.User;
import com.szq.javaweb.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if("/user/login".equals(servletPath))
        {
            doLogin(request,response);
        }else if("/user/exit".equals(servletPath)){
            doExit(request,response);
        }
    }

    protected void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.removeAttribute("user");
            session.invalidate();//手动销毁session对象
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //数据库连接
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        boolean success=false;

        try {
            conn = DBUtil.getConnection();
            String sql="select username,password from t_user where username= ? and password= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs.next()){
                success=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close(conn,ps,rs);
        }
        if(success){
            //获取session对象（这里要求是：必须获取到session，没有session也更要创建一个session对象）
            HttpSession session = request.getSession();
            User user=new User(username,password);
            session.setAttribute("user",user);

            String f = request.getParameter("f");
            if("f".equals(f)){
                Cookie cookie = new Cookie("username", username);
                Cookie cookie1 = new Cookie("password", password);

                cookie.setMaxAge(60*60*24*10);
                cookie1.setMaxAge(60*60*24*10);

                cookie1.setPath(request.getContextPath());
                cookie.setPath(request.getContextPath());

                response.addCookie(cookie);
                response.addCookie(cookie1);

            }

            response.sendRedirect(request.getContextPath()+"/dept/list");
        }else {
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }

    }
}
