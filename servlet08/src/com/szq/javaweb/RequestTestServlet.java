package com.szq.javaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RequestTestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> strings = parameterMap.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
//            System.out.println(key);
            String[] values = parameterMap.get(key);

            System.out.print(key+"=");

            for (String value:values)
            {
                System.out.print(value+",");
            }
            System.out.println();
        }

        Enumeration<String> names = request.getParameterNames();//获取key
        while (names.hasMoreElements())
        {
            String name = names.nextElement();
            System.out.println(name);
        }

        String[] usernames = request.getParameterValues("username");
        String[] passwords = request.getParameterValues("password");
        String[] intres = request.getParameterValues("itre");

        for (String username : usernames)
        {
            System.out.println(username);
        }

        for (String password : passwords)
        {
            System.out.println(password);
        }

        for (String intre : intres)
        {
            System.out.println(intre);
        }

        //通过name获取value这个一维数组的第一个元素
        //这个方法使用最多，因为这个一维数组只有一个元素
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] intress = request.getParameterValues("itre");

        //获取一维数组的第一个元素
        System.out.println(username);
        System.out.println(password);
       for (String intre : intress)
       {
           System.out.println(intre);
       }
    }
}
