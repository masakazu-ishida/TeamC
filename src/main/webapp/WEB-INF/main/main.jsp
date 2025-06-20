<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー</title>
<link  rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
	<h3>商品検索を行います</h3><br>
	<form action="/Shift_W/ItemsSearchController" method="get">
	キーワード<br>
	<input type="text" name="name"><br>
	カテゴリ<br>
			<select name='categoryId'>
				<c:forEach var='category' items='${categories}'>
					<c:if test="${category.categoryId == 0}">
					<option selected value='${category.categoryId}'>${category.name }</option>
					</c:if>
					<c:if test="${category.categoryId != 0}">
					<option value='${category.categoryId}'>${category.name }</option>
					</c:if>
				</c:forEach>
			</select><br />
	<input type ="hidden" name ="pageNumber" value = "1">
	<input type="submit" value="検索">
	
	</form>
	<a href="/Shift_W/CartListController">カートを見る</a><br>
	<a href=${ url}>${ urlName}</a>
</body>
</html>