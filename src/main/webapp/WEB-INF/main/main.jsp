<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー</title>
</head>
<body>
	<form action="/Shift_W/ItemsSearchController" method="post">
	キーワード<input type="text" name="name"><br>
	カテゴリ<br />
			<select name='categoryId'>
				<option selected value='0'>すべて</option>
				<option value='1'>帽子</option>
				<option value='2'>鞄</option>
			</select><br />
	
	<input type="submit" value="検索">
	
	</form>
	<a href="/Shift_W/CartListController">カートを見る</a><br>
	<a href="/Shift_W/UsersLoginController">ログイン</a>
</body>
</html>