<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
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
		border-radius:5px;
	}
	.btn {
		width:100%; 
		margin:0px auto;
		text-align:center;
	 }
</style>
<body>
<div class="wrap">
	<h1>게시글 보기</h1>
	<c:forEach items="${list}" var="item" varStatus="status">
	
	<form action="board-insert-process.do" name="board" method="post">
		<div>
			<p>제목<input type ="text" size=60 name="title" value="${item.title }"></p>
			
		</div>
		<div>
			<p>내용</p><textarea rows="20" cols="60" name="content" >${item.content }</textarea>
		</div>
		<input type="text" value="${sessionScope.user.u_name }" name="writer">	
		<input type="text" value="${sessionScope.user.u_idx}" name="u_idx">
	</form>
	
	<div class="btn">
		<input type="submit" value="확인">
	</div>
	</c:forEach>
	
</div>
</body>
</html>