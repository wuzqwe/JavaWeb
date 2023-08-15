<%@ page import="java.util.List" %>
<%@ page import="com.szq.javaweb.bean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入标签库，这里引入的jstl的核心标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Student student=new Student("s","df");
    Student student1=new Student("s2","sd");
    List<Student> students=new ArrayList<>();
    students.add(student);
    students.add(student1);
    request.setAttribute("list",students);
%>
<c:forEach items="${list}" var="s">
name:${s.name}<br>
address:${s.address}
</c:forEach>


<c:choose>
    <c:when test="${param.age<18}">
        中年
    </c:when>
</c:choose>