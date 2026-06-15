<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
   <link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>
	<h3>商品の詳細表示</h3>
	<table>
	<tr>
	<th>商品名</th><td>${item.name }</td>
	</tr>
	<tr>
	<th>商品の色</th><td>${item.color }</td>
	</tr>
	<tr>
	<th>メーカー名</th><td>${item.manufacturer}</td>
	</tr>
	<tr>
	<th>価格</th><td>${item.price}</td>
	</tr>
	<tr>
	<th>在庫数</th><td>${item.stock}</td>
	</tr>
	</table>
	
	<form action="" method="post">
	<select name="amount">
		<option value="1" selected>1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>	
	</select><br>
	
	<input type="hidden" name="itemId" value="${item.itemId }">
	<input type="submit" value="ショッピングカートに入れる">
	</form>
	
	<p>※ログインしてない状態では、ボタンのクリック後、<a href="">ログイン画面</a>に転送される</p>
	
	<a href="/TeamC/main">商品検索</a>へ
	

</body>
</html>