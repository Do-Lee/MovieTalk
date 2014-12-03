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
	<jsp:include page="./share/header.jsp"></jsp:include>

	<div class="container" align="center">
		<form class="form-horizontal" method="GET" action="FBAuthServlet.do" >
			<input type="hidden" name="fbid" value="${me.id}">
			<input type="hidden" name="name" value="${me.name}">
		</form>
		<form class="form-horizontal" method="POST" action="fbuser">
			 <div class ="form-group">
				<fieldset>
					<c:if test="${method == 'PUT'}">
						<input type="hidden" name="id" value="${fbuser.id}" />
						<input type="hidden" name="_method" value="PUT" />
					</c:if>

					<input type="hidden" name="fbid" value="${me.id}">
					<input type="hidden" name="name" value="${me.name}">
	
					<div class="form-action">
						<c:choose>
							<c:when test="${method=='POST'}">
								<div class="form-group">
									<button type="submit" class="btn btn-primary">Register</button>
								</div>
							</c:when>
						</c:choose>
					</div>
				</fieldset>
			</div>
		</form>
	</div>
	<jsp:include page="./share/footer.jsp"></jsp:include>
</body>
</html>