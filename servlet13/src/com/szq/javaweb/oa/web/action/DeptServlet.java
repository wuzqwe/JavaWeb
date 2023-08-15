package com.szq.javaweb.oa.web.action;

import com.szq.javaweb.oa.bean.Dept;
import com.szq.javaweb.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet({"/dept/detail","/dept/list","/dept/delete","/dept/modify","/dept/save"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if("/dept/list".equals(servletPath)){
            doList(request,response);
        }else if("/dept/detail".equals(servletPath)){
            doDetail(request,response);
        }else if("/dept/delete".equals(servletPath)){
            doDel(request,response);
        }else if("/dept/modify".equals(servletPath)){
            doModify(request,response);
        }else if("/dept/save".equals(servletPath)){
            doAdd(request,response);
        }

    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
        else
        {
//            request.getRequestDispatcher("/delete.html").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/delete.jsp");
        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count =0;

        try {
            conn = DBUtil.getConnection();
            String sql="update dept1 set dname = ?,loc = ? where deptno = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setString(3,deptno);
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close(conn,ps,rs);
        }
        if(count==1)
        {
            //更新成功
//            request.getRequestDispatcher("/dept/list").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
        else
        {
//            request.getRequestDispatcher("/delete.html").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/delete.html");
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            response.sendRedirect(request.getContextPath()+"/delete.jsp");
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String deptno = request.getParameter("deptno");
        String f = request.getParameter("f");
        //连接数据库，根据部门编号查询部门信息
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Dept dept=new Dept();
        try {

            conn = DBUtil.getConnection();
            String sql="select deptno,dname,loc from dept1 where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs = ps.executeQuery();
            if (rs.next())
            {
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");
                dept.setLoc(loc);
                dept.setDeptno(deptno);
                dept.setDname(dname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("dept",dept);
        if("d".equals(f))
        {
            request.getRequestDispatcher("/detail.jsp").forward(request,response);
        }
        else if("m".equals(f))
        {
            request.getRequestDispatcher("/edit.jsp").forward(request,response);
        }

    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取应用的更路径
        String contextPath = request.getContextPath();


        //连接数据库
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Dept> depts = new ArrayList<>();
        try {
            //连接数据库
            conn = DBUtil.getConnection();
            //获取预编译数据库操作对象
            String sql="select deptno,dname,loc from dept1";
            ps = conn.prepareStatement(sql);
            //执行SQL语句
            rs = ps.executeQuery();
            //处理结果集
            int i=0;
            while(rs.next())
            {
                String deptno=rs.getString("deptno");
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");

                Dept dept=new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);

                depts.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //释放资源
            DBUtil.close(conn,ps,rs);
        }

        //将一个集合放到请求域中
        request.setAttribute("deptList",depts);

        //转发
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
