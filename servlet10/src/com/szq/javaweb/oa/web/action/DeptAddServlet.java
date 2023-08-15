package com.szq.javaweb.oa.web.action;

import com.szq.javaweb.oa.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();

        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count=0;

        try {
             conn = DBUtil.getConnection();
             conn.setAutoCommit(false);
             String sql="insert into dept1(deptno,dname,loc) values (?,?,?)";
             ps = conn.prepareStatement(sql);
             ps.setString(1,deptno);
             ps.setString(2,dname);
             ps.setString(3,loc);
             count = ps.executeUpdate();
             conn.commit();

        } catch (SQLException e) {
            if(conn!=null)
            {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            DBUtil.close(conn,ps,rs);
        }


        if(count==1)
        {
//            request.getRequestDispatcher("/dept/list").forward(request,response);
//            使用重定向,发送俩次请求
            response.sendRedirect(contextPath+"/dept/list");
        }
        else
        {
//            request.getRequestDispatcher("/delete.html").forward(request,response);
            response.sendRedirect(contextPath+"/delete.html");
        }
    }
}
