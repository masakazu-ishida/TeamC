<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート内の商品を削除</title>
<link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>
<h3>以下の商品をショッピングカートから削除しました。</h3>
		<br/>
		<c:out value="${item.items.name}"></c:out><br/>
		<c:out value="${item.items.manufacturer}"></c:out><br/>
		<fmt:formatNumber value="${item.items.price}" />円<br/>
		<c:out value="数量${item.amount}個"></c:out><br/>
		<br>
		
		<a href="${pageContext.request.contextPath}/cart">カート一覧</a>へ<br />
		<a href="${pageContext.request.contextPath}/main">商品検索</a>へ<br />
		
</body>
</html>