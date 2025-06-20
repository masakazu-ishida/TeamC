<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>商品の購入確認</title>
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
<body>
		<h3>以下の商品を購入しますか？</h3>
		<br />
		<table>
			<tr>
				<th>商品名</th>
				<th>商品の色</th>
				<th>メーカー名</th>
				<th>単価</th>
				<th>数量</th>
			</tr>
			<c:forEach var="cart" items="${cartList}">
				<tr>
					<td>${cart.items.name}</td>
					<td>${cart.items.color}</td>
					<td>${cart.items.manufacturer}</td>
					<td>${cart.items.price}円</td>
					<td>${cart.amount}個</td>
				</tr>
			</c:forEach>
		</table>
		合計 18900 円<br /><br />
		<form action='/Shift_W/purchase' method='POST'>
			清算方法<br />
			<select name='payment'>
				<option selected>代金引換</option>
			</select><br /><br />
			配送先<br />
			<input type='radio' name='destination' value='registered' checked />ご自宅<br /><br />
			<input type='radio' name='destination' value='another' />配送先を指定<br />
			ご住所<br />
			<input type='text' name='address' /><br /><br />
			購入しますか？<br />
			<input type='submit' value='購入する' />
		</form>
		<br /><br />
		<a href='main.html'>商品検索</a>へ<br />
	</body>

</html>