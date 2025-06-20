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
		<h3>キーワード " ${keyword} " カテゴリ " ${category} " の検索結果 </h3>
		<table>
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
		</table>
		前へ
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category}&pageNumber=1">1</a>
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category}&pageNumber=2">2</a> 
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category}&pageNumber=3">3</a>
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category}&pageNumber=4">4</a>
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category}&pageNumber=5">5</a>
		<a href = "/Shift_W/ItemsSearchController?name=${keyword}&categoryId=${category}&pageNumber=2">次へ</a>
		<br>
		
		<a href = "/Shift_W/MainController">商品検索</a>へ

		</body>
</html>