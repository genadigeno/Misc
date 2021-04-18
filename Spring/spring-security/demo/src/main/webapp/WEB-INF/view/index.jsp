<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
 Index

 <br>

<%-- <a href="./logout">Log out</a>--%>
 <form action="${pageContext.request.contextPath}/logout" method="post">
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
     <button type="submit">Log out</button>
 </form>
</body>
</html>