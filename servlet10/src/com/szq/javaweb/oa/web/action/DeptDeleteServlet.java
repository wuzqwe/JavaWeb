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

public class DeptDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptno = request.getParameter("deptno");

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count = 0;

        try {
            conn = DBUtil.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            String sql="delete from dept1 where deptno =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            count = ps.executeUpdate();
            //事务提交
            conn.commit();

        } catch (SQLException e) {
            //遇到异常要回滚
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
            //删除成功
            //仍然跳转部门列表页面
//            request.getRequestDispatcher("/dept/list").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
        else {
            //删除失败
//            request.getRequestDispatcher("/delete.html").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/delete.html");
        }

    }

}
