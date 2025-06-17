<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='style.css' />
<title>カート一覧</title>
</head>
<body>
	<table>
		<tr>
			<th>商品名</th>
			<th>商品の色</th>
			<th>メーカー名</th>
			<th>単価</th>
			<th>数量</th>
			<th></th>
		</tr>
		<c:forEach var="item" items="${cartList} }">
			<tr>
				<td>${item.name }</td>
				<td>${item.name}</td>
				<td>${item.color}</td>
				<td>${item.manufacturer}</td>
				<td>${item.price}</td>
				<td>${item.amount}</td>
			</tr>
		</c:forEach>
	</table>
	${totalAmount }

</body>
</html>