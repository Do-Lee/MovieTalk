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
	<jsp:include page="share/header.jsp"></jsp:include>

	<div class="container">
		<div class="alert alert-error">
			<c:out value="${error}" />

			<c:if test="${errorMsg != null || errorMsg.size() > 0 }">
				<h3>Errors:</h3>
				<ul>
					<c:forEach var="msg" items="${errorMsgs}">
						<li><c:out value="${msg}" escapeXml="false"/></li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
	</div>

	<jsp:include page="./share/footer.jsp"></jsp:include>

</body>
</html>
