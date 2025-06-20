<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート削除</title>
</head>
<body>

		<h3>以下の商品をショッピングカートから削除しました。</h3>
		<br />
		<c:out value="${name}"/><br />
		<c:out value="${manufasuture}"/><br />
		<c:out value="${price}"/><br />
		<c:out value="${amount}"/><br /><br />
		<a href='/Shift_W/MainController'>商品検索</a>へ<br />
		
</body>
</html>