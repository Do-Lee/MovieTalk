<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Talk</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<script src="./js/jquery-1.11.1.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="share/header.jsp"></jsp:include>

	<div class="container">
		<div class="alert alert-success"><c:out value="${msg}" escapeXml="false"/></div>
		<div class="form-action">
			<a href="user" class="btn">목록으로</a>
		</div>
	</div>

	<jsp:include page="./share/footer.jsp"></jsp:include>

</body>
</html>