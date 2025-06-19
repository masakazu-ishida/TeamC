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
		麦わら帽子<br />
		日本帽子製造<br />
		4980円<br />
		数量 2 個<br />
		<form action='removeFromCartCommit.html' method='POST'>
			<input type='hidden' name='itemId' value='123' />
			<input type='submit' value='削除する' /><br />
		</form>
		<a href='main.html'>商品検索</a>へ<br />


</body>
</html>