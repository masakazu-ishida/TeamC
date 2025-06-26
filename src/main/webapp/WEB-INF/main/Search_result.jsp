<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta  http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>検索結果</title>
		<link rel="stylesheet" type="text/css" href="style.css" >
		
	</head>
		<body style="margin: auto; text-align: center;">
		<h3>キーワード " ${keyword} " カテゴリ " ${category.name} " の検索結果 </h3>
		<table style="margin: auto;">
			<tr>
				<th>商品名</th>
				<th>商品の色</th>
				<th>メーカー名</th>
				<th>価格</th>
			</tr>
			<c:forEach var="item" items="${ItemsDTO}" >
			<tr>
			  	<td>
			  		<a href= "/Shift_W/ItemsDetailController?itemId=${item.itemId}">
			  		${item.name}</a><c:if test="${item.recommended == true}"><sup>オススメ！</sup></c:if>
			  	</td>
			  	<td>${item.color}</td>
			  	<td>${item.manufacturer}</td>
			  	<td>${item.price}円</td>
			</tr>
			</c:forEach>
		</table><br>
		
		<c:if test="${pageNumber > 1}"><a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category.categoryId}&pageNumber=${pageNumber-1}">前へ</a></c:if>
		<c:if test="${pageNumber == 1}">前へ</c:if>
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category.categoryId}&pageNumber=1">1</a>
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category.categoryId}&pageNumber=2">2</a> 
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category.categoryId}&pageNumber=3">3</a>
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category.categoryId}&pageNumber=4">4</a>
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category.categoryId}&pageNumber=5">5</a>
		<c:if test="${pageNumber < 5}"><a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category.categoryId}&pageNumber=${pageNumber+1}">次へ</a></c:if>
		<c:if test="${pageNumber > 4}">次へ</c:if>
		<br><br>
		
		<a href = "/Shift_W/MainController">商品検索</a>へ

		</body>
</html>