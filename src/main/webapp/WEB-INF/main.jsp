<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
</head>
<body>
	<h3>商品の検索を行います</h3>
	
	<form action="/TeamC/itemSearch" method="get">
	
	キーワード<br>
	<input type="text" name="keyword"><br>
	カテゴリ<br>
	<select name="category">
		<option value="1" selected>すべて</option>
		<option value="2">帽子</option>
		<option value="3">鞄</option>
	</select><br>

	<input type="submit" value="検索">
	</form>
	
	<a href="/TeamC/cart">ショッピングカートを見る</a>
	
	<br>
	
	
	<c:if test="${empty userId}">
    <a href="/TeamC/Login2">ログイン</a><br>
	</c:if>
	
	<br>
	
	<c:if test="${not empty userId}">
    <a href="">会員情報の変更</a><br>
	</c:if>
	
	
	
</body>
</html>