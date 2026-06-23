<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
 <link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>
<h3>ログインしてください。</h3>
<p>${message}</p>
<form action="/TeamC/LoginServlet" method="post">
<table>

<tr><th>会員ID</th><td><input type="text" name="userId"></td></tr>
<tr><th>パスワード</th><td><input type="password" name="password"></td></tr>
<tr><td colspan="2"><input type="submit" value="ログイン"></td></tr>
</table>
</form>
</body>
</html>