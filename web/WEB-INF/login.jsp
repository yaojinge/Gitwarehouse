<%--
  Created by IntelliJ IDEA.
  User: chen.nie
  Date: 2018-12-26
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/login" method="post">

    username:<input type="text" name="username" value="${id}"/><br/>
    password:<input type="password" name="password"/><br/>
    图形验证码:<input type="text" name="imageCode"/>
    <br/>
    <input type="submit" value="登录"/>
<input type="hidden" name="_cstf" value="${_cstf.token}"/>
    <span style="color:red">${msg}</span>
    <span style="color:red">${defaultValue}</span>
${errorMsg}
</form>
<%--<form action="/j_security_check" method="post">--%>
<%--<input type="text" name="j_username"/></br>--%>
    <%--<input type="password" name="j_password"/></br>--%>
    <%--<input type="submit" value="认证"/>--%>
<%--</form>--%>
</body>
</html>
