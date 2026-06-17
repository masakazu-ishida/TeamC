<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<c:out value="${item.items.name}"></c:out>
	</td>
	<td>
	<c:out value="${item.items.color}"></c:out>
	</td>
	<td>
	<c:out value="${item.items.manufacturer}"></c:out>
	</td>
	<td>
	<c:out value="${item.items.price}"></c:out>
	</td>
	<td>
	<c:out value="${item.amount}"></c:out>
	</td>
	</tr>
	</c:forEach>
	</table>
	<p>合計<fmt:formatNumber value="${userPrice}" />円</p>
	
	<form action="" method="post">
	<p>清算方法</p>
	<select name="destination">
		<option value="1" selected>代金引換</option>
	</select>
	
	<p>配送先</p>
	<input type="radio" name="addres" value="home">ご自宅
	<input type="radio" name="addres" value="nohome">ご配送先を指定
	<p>ご住所</p>
	<input type="text" name="destination">
	
	<p>購入しますか？</p>
	<input type="submit" value="購入する">
	</form>
	
	<a href="/TeamC/main">商品検索</a>へ

</body>
</html>