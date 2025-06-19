<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>注文のキャンセル</title>
		<link rel='stylesheet' type='text/css' href='../style.css' />
	</head>
	<body>
		<h3>以下の注文をキャンセルしてよろしいですか？</h3>
		<br />
		<table>
			<tr>
				<th>注文者ID</th>
				<td>${purchase.purchaseId}</td>
			</tr>
			<tr>
				<th>注文日</th>
				<td>${purchase.purchasedDate}</td>
			</tr>
			<tr>
				<th>購入商品</th>
				<td>
					<table>
						<tr>
							<th>商品名</th>
							<th>色</th>
							<th>メーカー</th>
							<th>単価</th>
							<th>数量</th>
						</tr>
						
						<c:forEach var="det" items="${purchase.purchaseDetails}">
								<tr>
									<td>${det.item.name}</td>
									<td>${det.item.color}</td>
									<td>${det.item.manufacturer}</td>
									<td>${det.item.price}</td>
									<td>${det.amount}</td>
								</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<th>配送先</th>
				<td>自宅</td>
			</tr>
			<tr>
				<td colspan='2'>
					<form action='purchaseCansel' method='POST'>
						<input type='hidden' name='purchaseId' value="${purchase.purchaseId}" />
						<input type='submit' value='キャンセル' /><br />
					</form>
				</td>
			</tr>
		</table>
		<br />
		<a href='main'>管理者メインページ</a>へ<br />
	</body>
</html>