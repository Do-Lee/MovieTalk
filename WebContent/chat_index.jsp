<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Talk</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/common.css">
<script src="./js/jquery-1.11.1.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="./share/header.jsp"></jsp:include>

	<div class="container" align="center">
		<div class="row">
			<div class="span12 page-info">
				<div class="pull-left">
					Total <b>${chats.numItems}</b> users
				</div>
				<div class="pull-right">
					<b>${chats.page }</b> page / total <b>${chats.numPages }</b> pages
				</div>
			</div>
		</div>
		<div class="table-responsive">
			<table class="table table-bordered table-stripped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Movie Title</th>
						<th>Chat Title</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="chat" items="${chats.list }">
						<tr>
							<td><a href="chat.jsp?title=${chat.title}"><c:out
										value="${chat.opener}" /></a></td>
							<td><c:out value="${chat.movietitle}" /></td>
							<td><c:out value="${chat.title}" /></td>
							<td><a href="chat?op=delete&id=${chat.id}"
								class="btn btn-mini btn-danger" data-action="delete"
								data-id="${chat.id}">delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<jsp:include page="./page.jsp">
			<jsp:param name="currentPage" value="${users.page}" />
			<jsp:param name="url" value="user" />
			<jsp:param name="startPage" value="${users.startPageNo}" />
			<jsp:param name="endPage" value="${users.endPageNo}" />
			<jsp:param name="numPages" value="${users.numPages}" />
		</jsp:include>
	</div>

	<jsp:include page="./share/footer.jsp"></jsp:include>
</body>
</html>