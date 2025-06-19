<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メインページ</title>
</head>
<body>
		<h3>管理者メインページ</h3>
		<h4>購入履歴の検索</h4>
		<form action='../../Shift_W/admin/purchasesHistory' method='GET'>
			<table>
				<tr>
					<th>会員ID</th>
					<td><input type='text' class='id' name='user_id' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='検索' /></td>
				</tr>
			</table>
		</form>
</body>
</html>