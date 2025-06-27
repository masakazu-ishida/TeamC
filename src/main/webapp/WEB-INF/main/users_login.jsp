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
	<body style="margin: auto; text-align: center;">
		<h3 class="heading-29">ログインしてください。</h3>
		<br />
		<h4 style="color: red"><c:out value="${messeage}"/></h4>
		<form action='/Shift_W/UsersLoginController' method='POST'>
			<table style="margin: auto; text-align: center;">
				<tr>
					<th>会員ID</th>
					<td><input type='text' class='id' name='user_id' /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' class='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='ログイン' /></td>
				</tr>
			</table>
			
			
			
			<input type="hidden" name="source" value="${source}">
			<input type="hidden" name="itemId" value="${itemId}">
			<input type="hidden" name="amount" value="${amount}">
			<input type="hidden" name="date" value="${date}">
			
		</form>
	</body>
</html>