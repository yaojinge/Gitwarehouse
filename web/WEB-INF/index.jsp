<%--
  Created by IntelliJ IDEA.
  User: chen.nie
  Date: 2018-12-03
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" url="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form method="post" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="上传文件"/>
</form>
<c:if test="${user.username=='admin'}">
    <h1>admin</h1>
</c:if>
<c:if test="${user.username=='tomcat'}">
    <h2>tomcat</h2>
</c:if>
<c:if test="${user.username=='user'}">
    <h3>user</h3>
</c:if>
</body>
</html>
