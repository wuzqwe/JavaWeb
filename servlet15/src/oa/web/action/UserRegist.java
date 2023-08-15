package oa.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oa.utils.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/user/reg")
public class UserRegist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword=request.getParameter("repassword");

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count=0;

        if(repassword.equals(password)) {
            try {
                conn = DBUtil.getConnection();
                String sql = "insert into t_user(nickname,username,password) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, nickname);
                ps.setString(2, username);
                ps.setString(3, password);
                count = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn, ps, rs);
            }
        }else
        {
           response.sendRedirect(request.getContextPath()+"/reg.jsp");
        }
        if (count==1)
        {
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }
}
