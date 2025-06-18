<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta  http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>検索結果</title>
		<link  rel='stylesheet' type='text/css' href='style.css' />
	</head>
		<body>
		<h3>キーワード  ${keyword}  カテゴリ  ${category}  の検索結果 </h3>
		<table>
			<tr>
				<th>商品名</th>
				<th>商品の色</th>
				<th>メーカー名</th>
				<th>価格</th>
			</tr>
			<c:forEach var="item" items="${ItemsDTO}" >
			<tr>
			  	<td>${item.name}</td>
			  	<td>${item.color}</td>
			  	<td>${item.manufacturer}</td>
			  	<td>${item.price}</td>
			</tr>
			</c:forEach>
		</table>
		前へ1234次へ

		</body>
</html>