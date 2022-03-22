<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글달기</title>
</head>
<style>
</style>

<body>
	<form action="board-reply-insert-process.do" name="board" method="post">
		<input type="hidden" name="group" value="${board.group}">
		<input type="hidden" name="depth" value="${board.depth}">
		<input type="hidden" name="order" value="${board.order}">
		<h2>답글달기</h2>
		<div>
			<p>제목<input type ="text" size=60 name="title"></p>
		</div>
		<div>
			<p>내용</p><textarea rows="20" cols="60" name="content"></textarea>
		</div>
		<input type="text" value="${sessionScope.user.u_name }" name="writer">
		<input type="text" value="${sessionScope.user.u_idx}" name="u_idx">
		<input type="submit" value="글쓰기">
		
	</form>
</body>
</html>