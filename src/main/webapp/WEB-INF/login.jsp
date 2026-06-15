<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h1>ログインしてください</h1>
<p>${message}</p>
<form action="/TeamC/LoginServlet" method="post">
<p>会員ID<input type="text" name="userId"></p>
<p>パスワード<input type="password" name="password"></p>
<input type="submit" value="ログイン">
</form>
</body>
</html>