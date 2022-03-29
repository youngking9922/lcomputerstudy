<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<title>회원목록2</title>
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
<h1>회원 목록</h1>
	<table >
		<tr>
			<td colspan="4">전체 회원 수 : ${usercount}</td>
		</tr>
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
			<th>관리자</th>
		</tr>
		<c:forEach items="${list}" var="item" varStatus="status">
			 <tr>
				<td><a href="user-detail.do?u_idx=${item.u_idx}">${item.ROWNUM}</a></td>
				<td class="user_id">${item.u_id}</td>
				<td>${item.u_name}</td>
				<td><button type="button" class="add_admin_btn"value="${item.u_idx}">추가</button> <button class="delete_admin_btn" type="button"value="${item.u_idx}">제거</button></td>
		     <tr>
		</c:forEach>
	</table>
	<div>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.prevPage >= 1}">
					<li>
						<a href="user-list.do?page=${pagination.prevPage}">
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
								<a href="user-list.do?page=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			 <c:choose>
				<c:when test="${ pagination.nextPage <= pagination.lastPage }">
					<li style="">
						<a href="user-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 

		</ul>
	</div>
</body>
<script>

$(document).on('click', '.add_admin_btn', function () {
	user_idx = $(this).val();
	console.log(user_idx);
	
	$.ajax({
		url:'user-add-admin.do',
		type:'post',
		dataType:'html',
		data:{ user_idx : user_idx,
		},
		success : function(result){ 

	    }
	})
	
});

$(document).on('click', '.delete_admin_btn', function () {
	user_idx = $(this).val();
	console.log(user_idx);
	
	$.ajax({
		url:'user-delete-admin.do',
		type:'post',
		dataType:'html',
		data:{ user_idx : user_idx,
		},
		success : function(result){ 

	    }
	})
	
});
</script>

</html>
