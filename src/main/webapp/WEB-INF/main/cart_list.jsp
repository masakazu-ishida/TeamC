<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='style.css'>
<title>カート一覧</title>
</head>
<body>
	<h3>ショッピングカート内の商品一覧</h3>
	<table>
		<tr>
			<th>商品名</th>
			<th>商品の色</th>
			<th>メーカー名</th>
			<th>単価</th>
			<th>数量</th>
			<th></th>
		</tr>
		<c:forEach var="cart" items="${cartList}">
			<tr>
				<td>${cart.items.name}</td>
				<td>${cart.items.color}</td>
				<td>${cart.items.manufacturer}</td>
				<td>${cart.items.price}</td>
				<td>${cart.amount}</td>
				<td><a href = "/Shift_W/CartDeleteCheckController?itemId=${cart.items.itemId }" >削除</a></td>
			</tr>
		</c:forEach>
	</table>
	合計 ${totalAmount }円<br>
	<form action="/Shift_W/purchaseConfirm" method="post">
		<input type="submit" value="購入">
	</form>
	<br>
	<a href="/Shift_W/MainController">商品検索</a>へ

</body>
</html>