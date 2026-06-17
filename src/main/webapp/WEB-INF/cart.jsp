<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート</title>
<link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>
<h3>ショッピングカート内の商品一覧</h3><br/>
		<table>
			<tr>
				<th>商品名</th>
				<th>商品の色</th>
				<th>メーカー名</th>
				<th>単価</th>
				<th>数量</th>
				<th></th>
			</tr>
			<c:forEach var="item" items="${cart}">
			<tr>
				<td><c:out value="${item.items.name}"></c:out></td>
				<td><c:out value="${item.items.color}"></c:out></td>
				<td><c:out value="${item.items.manufacturer}"></c:out></td>
				<td><c:out value="${item.items.price}"></c:out></td>
				<td><c:out value="${item.amount}"></c:out></td>
				<td><a href="">削除</a></td>
			</tr>
			</c:forEach>
		</table>
		合計  <fmt:formatNumber value="${userPrice}" />円<br/>
		<form action="/TeamC/purchaseConfirm" method="post">
			<input type="submit" value='購入する' />
		</form>
		<br />
		<a href="/TeamC/main">商品検索</a>へ<br />

</body>
</html>