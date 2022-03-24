<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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