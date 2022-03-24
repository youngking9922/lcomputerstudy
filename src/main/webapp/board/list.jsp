<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<title>게시글 목록</title>
</head>
<style>
	body {
		margin:0;
	}
	table {
		width:90%;
		text-align:center;
		border-collapse:collapse;
		margin:10px auto auto auto;
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
	.top {
		width:100%;
		margin:0 20px 0 0 ;
		display:inline-block;
	}
	.write_btn{
		margin: 0 0 0 5%;
		width:20%;
		text-align:center;
		border:1px solid #ededed;
		float:left;
		display:inline-block;
	}
	.search {
		margin: 0 5% 0 0 ;
		width:60%;
		float:right;
		text-align:right;
		display:inline-block;
	 }
</style>
<body>
	<h1>게시글 목록</h1>

	<div class="top">
		<div class="write_btn"><a href="board-write.do">글쓰기</a></div>
		<div class="search">
			<select name="search_option">
				<option value="1" selected>제목</option>
				<option value="2">제목+내용</option>
				<option value="3">작성자</option>
			</select>
			<input type="text" name="search_txt" size="40"><button class="search_btn"type="button">검색</button>
		</div>
	</div>

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
		
	<div>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.prevPage >= 1}">
					<li>
						<a href="board-list.do?page=${pagination.prevPage}">
							◀
						</a>
					</li>
				</c:when>
			</c:choose> 
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				
					<c:choose>
						<c:when test="${ pagination.page eq i }">
							
							<li style="background-color:#ededed;">
								<span>${i}</span>
							</li>
						</c:when>
						<c:when test="${ pagination.page ne i }">
							<li>
								<a href="board-list.do?page=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			 <c:choose>
				<c:when test="${ pagination.nextPage <= pagination.lastPage }">
					<li style="">
						<a href="board-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
</body>

<script>
$(document).on('click', '.search_btn', function () {
	search_option = $("select[name=search_option]").val();
	search_txt = $("input[name=search_txt]").val();
	console.log(search_option);
	console.log(search_txt);
	$.ajax({
		url:'board-search.do',
		type:'GET',
		dataType:'html',
		data:{ search_option : search_option,
			search_txt : search_txt,
		}
	})
	
	.done(function (data) {
		console.log(data);
	});
	
});
</script>
</html>