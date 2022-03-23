<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
		height:50px;
		margin:10px auto;
	}
	li {
		list-style:none;
		width:70px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin: 0 5px;
	}
	.comment {
		width:50%;
		text-align:center;
		margin : 20px auto 10px auto;
	}
	.comment.content{
		border:1px solid skyblue;
	}

</style>
<body>
<script type="text/javasciprt">

</script>
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
		<li><a href="board-write-reply.do?group=${item.group}&depth=${item.depth}&order=${item.order}">답글달기</a></li>
	</ul>
	
	<form action="board-comment-insert.do"name="form_comment" method="post">
		<div class="comment">
			<input type="text" name="comment" size="40" value="">
			<input type="hidden" name="b_idx" value="${item.b_idx}">
			<input type="hidden" name= "u_idx" value="${item.u_idx}">
			<input type="submit" class="insert_comment_btn" value="댓글달기">
		</div>
	</form>
	</c:forEach>
	
	
		<table>
			<tr>
				<th>댓글</th>
				<td>작성자</td>
				<td>댓글의 댓글달기</td>
				<td>댓글</td>
			</tr>
			
			<c:forEach items="${comment_list}" var="comment_item" varStatus="status">
				<form action="comment-comment-insert.do"name="form_comment" method="post">
						<input type="hidden" name="c_board_idx" value="${comment_item.c_board_idx}">
						<input type="hidden" name="c_uidx" value="${sessionScope.user.u_idx}">
						<input type="hidden" name="c_group" value="${comment_item.c_group}">
						<input type="hidden" name="c_order" value="${comment_item.c_order}">
						<input type="hidden" name="c_depth" value="${comment_item.c_depth}">	
					<tr>
						<td>${comment_item.comment}</td>
						<td>${comment_item.c_uidx}</td>
						<td><input type = "text" name="c_content" ><input type="submit" value="댓글달기"></td>
						<td>
							<button type="button" class="btnReply">댓글</button>
						</td>
					</tr>
				</form>
			</c:forEach>
			
			
		</table>


</div>
<script>
$(document).on('click', '.btnReply', function () {
	$.ajax({
		url:'comment-comment-insert-ajax.do',
		type:'GET',
		dataType:'json',
		data:'a=1',
		succcess:function(data){
			console.log(data);
		}
	});
});
</script>
</body>
</html>