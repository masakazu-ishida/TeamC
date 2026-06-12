<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
</head>
<body>
	<h1>商品の検索を行います</h1>
	
	<form action="" method="get">
	
	キーワード<br>
	<input type="text" name="keyword"><br>
	カテゴリ<br>
	<select name="category">
		<option value="0" selected>すべて</option>
		<option value="1">帽子</option>
		<option value="2">鞄</option>
	</select><br>

	<input type="submit" value="検索">
	</form>
	
	<a href="">ショッピングカートを見る</a><br>
	
	<p>※ログインしていない場合には以下を表示</p><br>
	<a href="">ログイン</a><br>
	
	<p>※ログイン済の場合には以下を表示</p>
	<a href="">会員情報の変更</a>
	
</body>
</html>