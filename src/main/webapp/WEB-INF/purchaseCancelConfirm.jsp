<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文済みのキャンセル確認</title>
<link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>
<h3>以下をキャンセルしてもよろしいですか？</h3>

<table class="outer-table">
<tr>
	<th>注文日</th>
	<td><c:out value="${purchase.purchasedDate}"></c:out></td>
</tr>

<tr>
	<th>購入商品</th>
	<td>
		<table class="inner-table">
			<tr>
				<th>商品名</th>
				<th>色</th>
				<th>メーカー</th>
				<th>単価</th>
				<th>数量</th>
				<th>小計</th>
			</tr>
		
		<%-- 内ループ --%>
		<c:forEach var="detail" items="${purchase.list}">
			<tr>
				<td><c:out value="${detail.items.name}"></c:out></td>
				<td><c:out value="${detail.items.color}"></c:out></td>
				<td><c:out value="${detail.items.manufacturer}"></c:out></td>
				<td><fmt:formatNumber value="${detail.items.price}" />円</td>
				<td><c:out value="${detail.amount}個"></c:out></td>
				<%-- 小計の計算と表示 --%>
				<td><fmt:formatNumber value="${detail.items.price * detail.amount}"/>円</td>
				<%-- 合計金額の計算 --%>
				<c:set var="total" value="${total + (detail.items.price * detail.amount)}" />
			</tr>
		</c:forEach>
		</table>
	</td>
</tr>
	<tr>
		<th>合計金額</th>
		<td><fmt:formatNumber value="${total}" />円</td>
	</tr>
<tr>
	<th>配送先</th>
	<td>
	<c:choose>
		<c:when test="${empty purchase.destination}">自宅</c:when>
		<c:otherwise><c:out value="${purchase.destination}"></c:out></c:otherwise>
	</c:choose>
	</td>
</tr>

<tr>
	<td colspan="2">
		<form action="${pageContext.request.contextPath}/PurchaseCancelExecute" method="post">
			<input type="hidden" name="purchaseId" value="${purchase.purchaseId}">
			<input type="submit" value="キャンセル">
		</form>
	</td>
</tr>
</table>

<br>
<a href="${pageContext.request.contextPath}/main">商品検索へ</a>

</body>
</html>