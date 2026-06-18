<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入完了画面</title>
<link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>

<h3>以下の商品の購入を完了しました。</h3>

<table>
	<tr>
	<th>商品名</th><th>商品の色</th><th>メーカー名</th><th>単価</th><th>数量</th>
	</tr>
	<c:forEach var="item" items="${purchase}">
		<tr> <td><c:out value="${item.items.name}"></c:out></td>
            <td><c:out value="${item.items.color}"></c:out></td>
            <td><c:out value="${item.items.manufacturer}"></c:out></td>
            <td><c:out value="${item.items.price}"></c:out></td>
            <td><c:out value="${item.amount}"></c:out></td>
        </tr>
	</c:forEach>

</table>
	<p>合計<fmt:formatNumber value="${userPrice}" />円</p>
	
	<p>清算方法 ${par }</p>

	<p>配送先 <c:out value="${destination}"></c:out></p>
	


<a href="/TeamC/main">商品検索</a>へ
</body>
</html>