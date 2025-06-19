<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>以下の商品をショッピングカートから削除してよろしいですか？</h3>
		<br />
		<c:out value="${name}"/><br />
		<c:out value="${manufasuture}"/><br />
		<c:out value="${price}"/><br />
		<c:out value="${amount}"/><br /><br />
		<form action='/Shift_W/CartDeleteCheckController' method='POST'>
			<input type='hidden' name='itemId' value='123' />
			<input type='submit' value='削除する' /><br />
		</form>
		<a href='/Shift_W/ItemSearchController'>商品検索</a>へ<br />


</body>
</html>