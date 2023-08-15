<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门详情</title>
</head>
<body>
    <h1>部门详情</h1>
    <hr>
    部门编号：${dept.deptno}<br>
    部门名称：${dept.dname}<br>
    部门位置：${dept.loc}<br>
    <input type="button" value="后退" onclick="window.history.back()"/>
</body>
</html>