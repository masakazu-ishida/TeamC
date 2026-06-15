<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
    <link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>
	<h3>キーワード"${keyword }"カテゴリー"${category }"の検索結果</h3>
	
	<table>
	<tr>
	<th>商品名</th><th>商品の色</th><th>メーカー名</th><th>価格</th>
	</tr>
	
	
	<c:forEach var="item" items="${itemList}">
	<tr>
	<td><a href=""><c:out value="${item.name}"></c:out></a>
	<c:if test="${item.recommended}">
    <span class="osusume">オススメ！</span>
    </c:if>
	</td>
	<td><c:out value="${item.color}"></c:out></td>
	<td><c:out value="${item.manufacturer}"></c:out></td>
		<td><c:out value="${item.price}"></c:out>円</td>
	
	</tr>
	</c:forEach>
	</table>
	
	
	<a href="/TeamC/MainServlet">商品検索へ</a>

</body>
</html>