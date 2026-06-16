<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認</title>
   <link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>
	<h3>以下の商品を購入しますか？</h3>
	
	<table>
	<tr>
	<th>商品名</th><th>商品の色</th><th>メーカー名</th><th>単価</th><th>数量</th>
	</tr>
	<c:forEach var="item" items="${cart}">
	<tr>
	<td>
	<c:out value="${item.name}"></c:out>
	</td>
	<td>
	<c:out value="${item.color}"></c:out>
	</td>
	<td>
	<c:out value="${item.manufacturer}"></c:out>
	</td>
	<td>
	<c:out value="${item.price}"></c:out>
	</td>
	<td>
	<c:out value="${item.amount}"></c:out>
	</td>
	</tr>
	</table>
	合計${userPrice }円
	
	<form action="" method="post">
	清算方法
	<select name="destination">
		<option value="1" selected>代金引換</option>
	</select>
	
	配送先
	<input type="button" name="haisou" value="home">ご自宅
	<input type="button" name="haisou" value="sitei">配送先を指定
	ご住所
	<input type="text" name="zyuusyo">
	
	購入しますか？
	<input type="submit" value="購入する">
	</form>
	
	<a href="/TeamC/main">商品検索</a>へ

</body>
</html>