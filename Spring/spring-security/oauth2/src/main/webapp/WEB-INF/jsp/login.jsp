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

<div>
    <a href="/oauth2/authorization/facebook">log in with facebook</a>
</div>

<form action="/perform-login" method="post">
    <label for="user-name-label"></label><input type="text" name="username" id="user-name-label">
    <label for="password-label"></label><input type="password" name="password" id="password-label">

    <button type="submit">Log in</button>
</form>
</body>
</html>