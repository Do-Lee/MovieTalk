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
					Total <b>${movies.numItems }</b> users
				</div>
				<div class="pull-right">
					<b>${movies.page }</b> page / total <b>${movies.numPages }</b> pages
				</div>
			</div>
		</div>
		<div class="table-responsive">
			<table class="table table-bordered table-stripped">
				<thead>
					<tr>
						<th>Image</th>
						<th>Movie Title</th>
						<th>Director</th>
						<th>Chat Title</th>
					</tr>
				</thead>
				<tbody>
				<!-- db 수정후 다시  -->
					<c:forEach var="movie" items="${movies.list }">
						<tr>
							<td><c:out value="${movie.link}"/></td>
							<td><c:out value="${movie.title}"/></td>
							<td><c:out value="${movie.director}"/></td>
							<td><a href="chat?id=${movie.id}"><c:out value="${chat.title}"/></a>></td> 
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