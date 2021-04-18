<%--
  Created by IntelliJ IDEA.
  User: GENO
  Date: 2/20/2021
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="./oauth2/authorization/crypto-portfolio">Authorize By OAuth2.0</a>
    <a href="./oauth2/authorization/google">Google OAuth2.0</a>
</div>
<br>
<%--<form action="${pageContext.request.contextPath}/perform_login" method="post">--%>
<form action="./perform_login" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

    <label for="username">User Name</label>
    <input type="text" name="username" id="username">
    <br>
    <label for="password">User Name</label>
    <input type="password" name="password" id="password">
    <br>
    <label for="remember_me">Remember Me</label>
    <input type="checkbox" name="remember-me" id="remember_me">
    <br>
    <button type="submit">Log In</button>
</form>
</body>
</html>
