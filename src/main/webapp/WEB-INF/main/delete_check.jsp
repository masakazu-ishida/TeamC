<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート内の商品を削除</title>
</head>
<body>

<h3>以下の商品をショッピングカートから削除してよろしいですか？</h3><br>
			${cartItem.items.name}<br>
			${cartItem.items.color}<br>
			${cartItem.items.manufacturer}<br>
			${cartItem.items.price}円<br>
			数量${cartItem.amount}個<br>
			
		<form action='/Shift_W/CartDeleteController' method='POST'>
			<input type='hidden' name='itemId' value ='${itemId}' />
			<input type='submit' value='削除する' /><br>
		</form>
		<a href='/Shift_W/MainController'>商品検索へ</a><br>


</body>
</html>