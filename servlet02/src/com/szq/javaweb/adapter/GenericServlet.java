package com.szq.javaweb.adapter;

import jakarta.servlet.*;

import java.io.IOException;

/*
* 编写一个标准通用的Servlet,起名为GenericServlet
* 以后所有的Servlet类都不用直接实现Servlet接口了
* 以后所有类都要继承GenericServlet
* */
public abstract class GenericServlet implements Servlet {
    private ServletConfig config;
    @Override
    public final void init(ServletConfig config) throws ServletException {
        this.config=config;
        this.init();
    }

    public  void  init()
    {

    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    /**
     * 抽象方法，这个方法最常用。所要求子类实现service方法
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
