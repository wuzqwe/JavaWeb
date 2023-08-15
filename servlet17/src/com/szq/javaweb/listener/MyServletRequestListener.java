package com.szq.javaweb.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request请求对象被创建");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request请求对象被销毁");
    }
}
