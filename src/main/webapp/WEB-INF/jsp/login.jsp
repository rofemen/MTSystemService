<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">${error}</div>
<form action="/login" method="post">
    用户名：<input type="text" name="username" value="<shiro:principal/>"><br/> 
    密码：<input type="password" name="password"><br/>
    自动登录：<input type="checkbox" name="rememberMe"><br/>
    <input type="radio" name="userType" value="stu"/>学生
    <input type="radio" name="userType" value="tc"/>教师
    <input type="submit" value="登录">
</form>

</body>
</html>