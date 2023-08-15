<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>欢迎oa系统页面</title>
</head>

<body>
<!--前端超链接发送请求时，请求路径以"/"开始，并且要带着项目名-->
   <%--     <a href="<%=request.getContextPath()%>/dept/list">查看部门列表</a>--%>
<%--<%=request.getContextPath()%>--%>
<h1>用户登录</h1>
<hr>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    usrname<input type="text" name="username"><br>
    password<input type="password" name="password"><br>
    <input type="checkbox" name="f" value="1">十天内免登录<br>
    <input type="submit" value="login">
</form>
</body>
</html>