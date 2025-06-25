<%@page import="jp.co.shiftw.dto.ItemsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.Date"%>
    <%@ page import="java.text.SimpleDateFormat"%>
    <%@ page import="jp.co.shiftw.dto.ItemsDTO"%>
<!DOCTYPE html>
<html>
	<head>
		<meta  http-equiv='Content-Type' content='text/html; charset=UTF-8' />
		<title>検索詳細</title>
		<link  rel='stylesheet' type='text/css' href='style.css' />
</head>
<body>
<h3>商品の詳細表示</h3>
<table>
<tr>
	<th>商品名</th>
	<td>${dto.name }</td>
</tr>
<tr>
	<th>商品の色</th>
	<td>${dto.color }</td>
</tr>
<tr>
	<th>メーカー名</th>
	<td>${dto.manufacturer }</td>
</tr>
<tr>
	<th>価格</th>
	<td>${dto.price }円</td>
</tr>
<tr>
	<th>在庫数</th>
	<td>${dto.stock }個</td>
</tr>
</table>
数量
<form action="/Shift_W//CartAddController" method="post">
	<input type = "hidden" name = "itemId" value = "${dto.itemId}">
	<select name='amount' size="5">
	
	<% 
		ItemsDTO dto = (ItemsDTO)request.getAttribute("dto");
		for(int i = 1; i <= dto.getStock(); i ++) { %>
			<c:if test="<%= i == 1 %>">
					<option selected value='1'>1</option>
			</c:if>
			<c:if test="<%= i > 1 %>">
					<option value=<%= i %>><%= i %></option>
			</c:if>
	<%}%>
	</select><br />
	<%
    	Date now = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String formattedDate = sdf.format(now);
  	%>
	<input type = "hidden" name = "date" value = "<%= formattedDate %>">
	<c:if test="${dto.stock > 0}">
		<input type = "submit" value = "ショッピングカートに入れる">
	</c:if>
	
	<c:if test="${dto.stock == 0}">
		<p style="color: red; font-weight: bold;">売り切れ</p>
	</c:if>
	
</form>


<a href ="/Shift_W/MainController">商品検索</a>へ


</body>
</html>