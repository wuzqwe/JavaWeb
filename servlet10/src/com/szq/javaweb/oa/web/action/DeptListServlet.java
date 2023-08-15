package com.szq.javaweb.oa.web.action;

import com.szq.javaweb.oa.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
