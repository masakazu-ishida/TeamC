<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
   <link rel="stylesheet" href="<c:url value='/style.css' />">
</head>
<body>
<h3>購入履歴の一覧</h3>
<%-- 外（注文ごと）のテーブル --%>
<table class="outer-table">
<tr>
	<th>注文日</th>
	<th>購入商品</th>
	<th>配送先</th>
	<th>キャンセル</th>
</tr>
<%--外ループ --%>
<c:forEach var="purchase" items="${list}">
<tr>
	<%--注文日の表示 --%>
	<td>
		<c:out value="${purchase.purchasedDate}"></c:out>
	</td>
	<td>
	<%-- 中（商品詳細）のテーブル --%>
	<table class="inner-table">
<tr>
	<th>商品名</th>
	<th>色</th>
	<th>メーカー</th>
	<th>単価</th>
	<th>数量</th>
</tr>
	<%--内ループ --%>
	<c:forEach var="detail" items="${purchase.list}">
		<tr>
			<td><c:out value="${detail.items.name}"></c:out></td>
			<td><c:out value="${detail.items.color}"></c:out></td>
			<td><c:out value="${detail.items.manufacturer}"></c:out></td>
			<td><fmt:formatNumber value="${detail.items.price}" />円</td>
			<td><c:out value="${detail.amount} 個"></c:out></td>
		</tr>
	</c:forEach>
	</table>
</td>                
<td>
	<c:choose>
	<%-- 配送先がnullの場合自宅と表示 --%>
		<c:when test="${empty purchase.destination}">自宅</c:when>
	<%-- 値が入っている場合はその配送先を表示 --%>
		<c:otherwise><c:out value="${purchase.destination}"></c:out></c:otherwise>
	</c:choose>
</td>
                
<td>
	<c:choose>
        <%-- キャンセルがtrueの場合　キャンセル済みの表示 --%>
        <c:when test="${purchase.cancel}">
		</c:when>
        <%-- キャンセル可能な場合はリンクを表示 仮のサーブレット名 絶対パス--%>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/PurchaseCancelConfirm?purchaseId=${purchase.purchaseId}">キャンセル</a>
		</c:otherwise>
	</c:choose>
</td>
</tr>
</c:forEach>
</table>
<br>
    <a href="${pageContext.request.contextPath}/main">商品検索へ</a>
</body>
</html>