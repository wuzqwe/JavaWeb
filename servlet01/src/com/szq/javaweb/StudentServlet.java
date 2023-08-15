package com.szq.javaweb;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class StudentServlet implements Servlet {

    //init被翻译为初始化
    //init方法执行一次
    //在StudentServlet对象被第一次创建之后执行
    //init方法通常完成初始化操作
    //init方法执行的时候StudentServlet对象已经被创建出来
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //service方法：是处理用户请求的核心
    //只要用户发送一次请求，service方法必然会执行一次
    //发送100次请求，service就会执行100次
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        //设置响应的类型
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //连接数据库

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url="jdbc:mysql://localhost:3306/bjpowernode";
            String user="root";
            String password="137152001szq";
            conn=DriverManager.getConnection(url,user,password);
            //获取预编译数据库对象
            String sql="select id,name from t_vip";
            ps=conn.prepareStatement(sql);
            //执行sql语句
            rs=ps.executeQuery();
            while (rs.next())
            {
                String id=rs.getString("id");
                String name=rs.getString("name");
                out.print(id+","+name+"<br>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //释放资源
            if(rs!=null)
            {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null)
            {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null)
            {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }


    //destroy方法也只执行一次
    //Tomcat服务在销毁AServlet对象之前会调用一次destroy方法
    //destroy方法在执行的时候，对象的内存还没被销毁。即将被销毁
    //destroy方法可以编写销毁前的准备
    //比如说服务器的关闭的时候，对象开启了资源，这些资源可能是流，可能是数据连接
    //那么关闭服务器的时候，要关闭这些流，关闭这些数据连接，那么关闭这些流的代码可以写到destroy方法中
    @Override
    public void destroy() {

    }
}
