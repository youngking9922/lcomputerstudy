<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
</head>
<style>
	.wrap {
		width:100%;
		margin:0 auto;
	}
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
	}

</style>
<body>
<div class="wrap">
	<h1>게시글 보기</h1>
	<c:forEach items="${list}" var="item" varStatus="status">
		<table>
			<tr>
				<th>제목</th>
				<td>${item.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${item.content}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${item.writer}</td>
			</tr>
			<tr>
				<th>작성일시</th>
				<td>${item.date}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${item.view_count}</td>
			</tr>		
		</table>
		

	<ul>
		<li><a href="board-modify.do?b_idx=${item.b_idx}">수정</a></li>
		<li><a href="board-delete-process.do?b_idx=${item.b_idx}">삭제</a></li>
		<li><a href="board-list.do">목록</a></li>
	</ul>

	</c:forEach>
	
</div>
</body>
</html>