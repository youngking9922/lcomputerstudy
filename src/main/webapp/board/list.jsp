<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<style>
	table {
		border-collapse:collapse;
		margin:40px auto;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
	h1 {
		text-align:center;
	}
	ul {
		width:400px;
		height:500px;
		margin:10px auto;
	}
	li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin: 0 5px;
		border-radius:5px;
	}
</style>
<body>
	<h1>게시글 목록</h1>
		<table>
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일시</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${list}" var="item" varStatus="status">
				<tr>
					<td><a href="board-detail.do?b_idx=${item.b_idx}">${item.b_idx}</a></td>
					<td><a href="board-detail.do?b_idx=${item.b_idx}">${item.title}</a></td>
					<td>${item.writer}</td>
					<td>${item.date}</td>
					<td>${item.view_count}</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>