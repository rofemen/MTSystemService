<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
欢迎学习Shiro综合案例，更多案例请访问我的<a href="https://github.com/zhangkaitao">github</a>
<form action="testController/test" method="post"  enctype="multipart/form-data">
	<input type="file" name="file"/>
	<input type="submit" value="上传"/> 
</form>
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