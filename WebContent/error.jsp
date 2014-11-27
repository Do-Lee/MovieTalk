<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Talk</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- Latest compiled and JQuery -->
<script src="//code.jquery.com/jquery-lastest"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="share/header.jsp"></jsp:include>

	<div class="container">
		<div class="alert alert-error">
			<c:out value="${error}" />

			<c:if test="${errorMsg != null || errorMsg.size() > 0 }">
				<h3>Errors:</h3>
				<ul>
					<c:forEach var="msg" items="${errorMsgs}">
						<li>${msg}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
	</div>

	<jsp:include page="./share/footer.jsp"></jsp:include>

</body>
</html>
