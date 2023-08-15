<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门列表</title>
<%--    <base href="http://localhost:8080/oa3/">--%>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<script type="text/javascript">
    function del(dno)
    {
        if(window.confirm('亲，删了不可恢复哦'))
        {
            document.location.href='${pageContext.request.contextPath}/dept/delete?deptno='+dno
        }
    }
</script>
<h1>欢迎系统！！在线人数${outlinecount}人</h1>
<a href="user/exit">退出系统</a>

<h1 align="center">部门列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>
   <c:forEach items="${deptList}" varStatus="dept1Status" var="dept1">
       <tr>
           <td>${dept1Status.count}</td>
           <td>${dept1.deptno}</td>
           <td>${dept1.dname}</td>
           <td>
               <a href="javascript:void(0)" onclick="del(${dept1.deptno})">删除</a>
               <a href="dept/detail?f=m&deptno=${dept1.deptno}">修改</a>
               <a href="dept/detail?f=d&deptno=${dept1.deptno}">详情</a>
           </td>
       </tr>
   </c:forEach>

</table>
<a href="add.jsp">新增部门</a>

<script>

</script>
</body>
</html>
