<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>会員ログイン</title>
		<link rel='stylesheet' type='text/css' href='style.css' />
	</head>
	<body>
		<h3>管理者ログイン</h3>
		<br />
		<h4><c:out value="${messeage}"/></h4>
		<form action='/Shift_W/Admin' method='POST'>
			<table>
				<tr>
					<th>管理者ID</th>
					<td><input type='text' class='id' name='adminId' /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' class='password' name='adminPassword' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='ログイン' /></td>
				</tr>
			</table>
		</form>
	</body>
</html>