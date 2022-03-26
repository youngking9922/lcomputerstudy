<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
	
		<div class="comment">
			<input type="text" name="comment" size="40" value="">
			<input type="hidden" name="b_idx" value="${item.b_idx}">
			<input type="hidden" name= "u_idx" value="${item.u_idx}">
			<input type="hidden" name="c_writer" value="${sessionScope.user.u_name}">
			<input type="hidden" name="c_group" value="0">
			<input type="hidden" name="c_order" value="0">
			<input type="hidden" name="c_depth" value="0">
			<button  class="insert_comment_btn" type="button">댓글달기</button>
		</div>
	</c:forEach>
	
	<div id="commentList">
		<table>
			<tr>
				<th>댓글</th>
				<td>작성자</td>
				<td>댓글의 댓글달기</td>
			</tr>
			
			<c:forEach items="${comment_list}" var="comment_item" varStatus="status">
					<input type="hidden" name="c_board_idx${comment_item.num}" value="${comment_item.c_board_idx}">
					<input type="hidden" name="c_uidx${comment_item.num}" value="${sessionScope.user.u_idx}">
					<input type="hidden" name="c_writer${comment_item.num}" value="${sessionScope.user.u_name}">
					<input type="hidden" name="c_group${comment_item.num}" value="${comment_item.c_group}">
					<input type="hidden" name="c_order${comment_item.num}" value="${comment_item.c_order}">
					<input type="hidden" name="c_depth${comment_item.num}" value="${comment_item.c_depth}">
				<tr>
					<td>${comment_item.comment}</td>
					<td>${comment_item.writer}</td>
					<td><button type="button" name = "${comment_item.num}" class="Replyshow_btn ${comment_item.num}">열기</button>
						<button type="button" name = "${comment_item.num}" class="Replyhide_btn">닫기</button>
					</td>
				</tr>
				<tr>
					<td colspan = '2' class ="show_content${comment_item.num}" style="display:none"><input type = "text" name="c_content${comment_item.num}" value=""></td>
					<td class ="show_content${comment_item.num}" style="display:none"><button type="button" name = "${comment_item.num}"  class="btnReply ${comment_item.num}">댓글달기</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</div>
<script>

$(document).on('click', '.Replyshow_btn', function () {
	var name_by_class = $(this).attr('name');
	$('.show_content'+name_by_class).attr('style', "display:'';");  
});


$(document).on('click', '.Replyhide_btn', function () {
	var name_by_class = $(this).attr('name');
	$('.show_content'+name_by_class).attr('style', "display:none;");  
});

$(document).on('click', '.insert_comment_btn', function () {
	c_board_idx = $("input[name=b_idx]").val();
	c_content = $("input[name=comment").val();
	c_uidx = $("input[name=u_idx").val();
	c_writer = $("input[name=c_writer").val();
	c_group = $("input[name=c_group").val();
	c_order = $("input[name=c_order").val();
	c_depth = $("input[name=c_depth").val();
	$.ajax({
		url:'board-comment-insert.do',
		type:'POST',
		dataType:'html',
		data:{ c_board_idx : c_board_idx,
			c_content : c_content,
			c_uidx : c_uidx,
			c_writer : c_writer,
			c_group : c_group,
			c_order : c_order,
			c_depth : c_depth,
		}
	})
	.done(function (data) {
		$('#commentList').html(data);
	});
});

$(document).on('click', '.btnReply', function () {
	var name_by_class = $(this).attr('name');
	c_board_idx = $("input[name=c_board_idx"+name_by_class+"]").val();
	c_content = $("input[name=c_content"+name_by_class+"]").val();
	c_uidx = $("input[name=c_uidx"+name_by_class+"]").val();
	c_writer = $("input[name=c_writer"+name_by_class+"]").val();
	c_group = $("input[name=c_group"+name_by_class+"]").val();
	c_order = $("input[name=c_order"+name_by_class+"]").val();
	c_depth = $("input[name=c_depth"+name_by_class+"]").val();
	
	$.ajax({
		url:'comment-comment-insert-ajax.do',
		type:'POST',
		dataType:'html',
		data:{ c_board_idx : c_board_idx,
			c_content : c_content,
			c_uidx : c_uidx,
			c_writer : c_writer,
			c_group : c_group,
			c_order : c_order,
			c_depth : c_depth,
		}
	})
	.done(function (data) {
		
		$('#commentList').html(data);
	});
});
</script>
</body>
</html>