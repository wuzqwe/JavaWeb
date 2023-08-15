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

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies=request.getCookies();
        String password=null;
        String username=null;
        if(cookies!=null)
        {
            for (Cookie cookie:cookies)
            {
                String name=cookie.getName();
                if("username".equals(name)){
                    username=cookie.getValue();
                }
                else if("password".equals(name))
                {
                    password=cookie.getValue();
                }
            }
        }
        if(username!=null&&password!=null)
        {
            Connection conn=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            boolean success=false;

            try {
                conn= DBUtil.getConnection();
                String sql="select * from t_user where username=? and password=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2,password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    success=true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBUtil.close(conn,ps,rs);
            }
            if (success){
                HttpSession session = request.getSession();
                User user=new User(username,password);
                session.setAttribute("user",user);
                response.sendRedirect(request.getContextPath()+"/dept/list");
            }else {
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }
        }else
        {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }
}
