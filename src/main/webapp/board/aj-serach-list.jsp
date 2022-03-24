<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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