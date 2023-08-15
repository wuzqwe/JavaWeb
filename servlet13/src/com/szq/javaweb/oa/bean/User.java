package com.szq.javaweb.oa.bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.util.Objects;

public class User implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        //用户登录
        //User类型对象向session中存放了
        ServletContext application = event.getSession().getServletContext();
        Object outlinecount = application.getAttribute("outlinecount");
        if (outlinecount==null){
            application.setAttribute("outlinecount",1);
        }else {
            int count=(Integer) outlinecount;
            count++;
            application.setAttribute("outlinecount",count);
        }

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
       //用户退出
        //User类型的对象从session域中删除了
        ServletContext application = event.getSession().getServletContext();
        Integer outlinecount=(Integer) application.getAttribute("outlinecount");
        outlinecount--;
        application.setAttribute("outlinecount",outlinecount);
    }

    private String username;
    private String password;

    public User(){

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
