package com.szq.javaweb.oa.web.action;

import com.szq.javaweb.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns= {"/dept/list","/dept/edit","/dept/detail","/dept/delete","/dept/modify","/dept/save"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if("/dept/list".equals(servletPath)){
            doList(request,response);
        }else if("/dept/edit".equals(servletPath)){
            doEdit(request,response);
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
            response.sendRedirect(request.getContextPath()+"/delete.html");
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
            response.sendRedirect(request.getContextPath()+"/delete.html");
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("         <!DOCTYPE html>");
        out.print(" <html lang='en'>");
        out.print(" <head>");
        out.print("     <meta charset='UTF-8'>");
        out.print("     <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.print("     <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.print("     <title>部门详情</title>");
        out.print(" </head>");
        out.print(" <body>");
        out.print("     <h1>部门详情</h1>");
        out.print("     <hr>");



        String deptno = request.getParameter("deptno");
        //连接数据库，根据部门编号查询部门信息
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {

            conn = DBUtil.getConnection();
            String sql="select deptno,dname,loc from dept1 where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs = ps.executeQuery();
            if (rs.next())
            {
                String deptno1=rs.getString("deptno");
                String dname=rs.getString("dname");
                String loc=rs.getString("loc");
                out.print("                 部门编号："+deptno1+" <br>");
                out.print("                 部门名称："+dname+"<br>");
                out.print("         部门位置："+loc+"<br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close(conn,ps,rs);
        }


        out.print("     <input type='button' value='后退' onclick='window.history.back()'/>");
        out.print(" </body>");
        out.print(" </html>");
    }


    private void doEdit(HttpServletRequest request, HttpServletResponse response) {
        String contextPath = request.getContextPath();
        response.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }


        out.print("  <!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.print("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.print("    <title>修改部门</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("    <h1>修改部门</h1>");
        out.print("    <hr>");
        out.print("    <form action='"+contextPath+"/dept/modify' method='post'>");





        String deptno = request.getParameter("deptno");


        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            conn = DBUtil.getConnection();
            String sql="select dname,loc from dept1 where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs = ps.executeQuery();
            if(rs.next())
            {

                String dname=rs.getString("dname");
                String loc=rs.getString("loc");
                out.print("        部门编号<input type='text' name='deptno' value='"+deptno+"' readonly/><br>");
                out.print("        部门名称<input type='text' name='dname' value='"+dname+"'/><br>");
                out.print("        部门位置<input type='text' name='loc' value='"+loc+"'/><br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close(conn,ps,rs);
        }

        out.print("        <input type='submit' value='修改'>");
        out.print("    </form>");
        out.print("</body>");
        out.print("</html>");
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取应用的更路径
        String contextPath = request.getContextPath();


        //浏览器页面
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("         <!DOCTYPE html>");
        out.print(" <html lang='en'>");
        out.print(" <head>");
        out.print("     <meta charset='UTF-8'>");
        out.print("     <title>部门列表</title>");

        out.print("   <script type='text/javascript'>");
        out.print("     function del(dno) {");
        out.print("       if(window.confirm('亲，删了不可恢复哦'))");
        out.print("       {");
        out.print("           document.location.href='"+contextPath+"/dept/delete?deptno='+dno");
        out.print("       }");
        out.print("   }");
        out.print("   </script>");

        out.print(" </head>");
        out.print(" <body>");
        out.print(" <h1 align='center'>部门列表</h1>");
        out.print(" <hr>");
        out.print(" <table border='1px' align='center' width='50%'>");
        out.print("     <tr>");
        out.print("         <th>序号</th>");
        out.print("         <th>部门编号</th>");
        out.print("         <th>部门名称</th>");
        out.print("         <th>操作</th>");
        out.print("     </tr>");
        out.print("     <tr>");


        //连接数据库
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;


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
                out.print("         <td>"+(i++)+"</td>");
                out.print("         <td>"+deptno+"</td>");
                out.print("         <td>"+dname+"</td>");
                out.print("         <td>");
                out.print("             <a href='javascript:void(0)' onclick='del("+deptno+")'>删除</a>");
                out.print("             <a href='"+contextPath+"/dept/edit?deptno="+deptno+"'>修改</a>");
                out.print("             <a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>详情</a>");
                out.print("         </td>");
                out.print("     </tr>");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //释放资源
            DBUtil.close(conn,ps,rs);
        }

        out.print("     ");
        out.print(" </table>");
        out.print(" <a href='"+contextPath+"/add.html'>新增部门</a>");
        out.print(" </body>");
        out.print(" </html>");
    }
}
