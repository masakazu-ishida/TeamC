<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta  http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>検索詳細</title>
		<link  rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
<h3>商品の詳細表示</h3>
<table>
<tr>
	<th>商品名</th>
	<td>${dto.name }</td>
</tr>
<tr>
	<th>商品の色</th>
	<td>${dto.color }</td>
</tr>
<tr>
	<th>メーカー名</th>
	<td>${dto.manufacturer }</td>
</tr>
<tr>
	<th>価格</th>
	<td>${dto.price }円</td>
</tr>
<tr>
	<th>在庫数</th>
	<td>${dto.stock }個</td>
</tr>
</table>
数量
<select name='amount'>
				<option selected value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>
				<option value='5'>5</option>
			</select><br />
<input type =""><br>
<a href = "/Shift_W/UsersLoginController">ログイン画面</a><br>
<a href ="/Shift_W/MainController">商品検索</a>へ


</body>
</html>