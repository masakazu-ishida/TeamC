<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>購入履歴一覧</title>
		<link rel='stylesheet' type='text/css' href='../style.css' />
	</head>
	<body>
		<h3>購入履歴の一覧</h3>
		<br />
		<table>
			<tr>
				<th>購入者ID</th>
				<th>注文日</th>
				<th>購入商品</th>
				<th>配送先</th>
				<th></th>
			</tr>
			<c:forEach var="pur" items="${purchases}" >
			
				<tr>
					<td>${pur.purchasedUser}</td>
					<td>${pur.purchasedDate}</td>
					<td>
						<table width="100%">
							<tr>
								<th>商品名</th>
								<th>色</th>
								<th>メーカー</th>
								<th>単価</th>
								<th>数量</th>
							</tr>
							<c:forEach var="det" items="${pur.purchaseDetails}">
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
					<td>${pur.destination}</td>
					<td><a href="">キャンセル</a></td>
				</tr>
					
			</c:forEach>
		</table>
		<br /><br />
		<a href='main.html'>管理者メインページ</a>へ<br />
	</body>
</html>