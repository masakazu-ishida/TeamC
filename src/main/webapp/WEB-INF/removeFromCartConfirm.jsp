<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート内の商品を削除</title>
<link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>

<h3>以下の商品をショッピングカートから削除してよろしいですか？</h3>
		<br/>
		<c:out value="${item.items.name}"></c:out><br/>
		<c:out value="${item.items.manufacturer}"></c:out><br/>
		<c:out value="${item.items.price}"></c:out><br/>
		<c:out value="${item.amount}"></c:out><br/>
		
		<form action="${pageContext.request.contextPath}/cartDeleteCommit" method="post">
			<input type="hidden" name="itemId" value="${item.itemId}" />
			<input type="submit" value="削除する" /><br />
		</form>
		<a href="/TeamC/main">商品検索</a>へ<br />

</body>
</html>