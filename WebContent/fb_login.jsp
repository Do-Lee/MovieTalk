<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Movie Talk</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css">
	<script src="./js/jquery-1.11.1.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="//connect.facebook.net/en_US/all.js"></script>
</head>
<body>
	<jsp:include page="./share/header.jsp"></jsp:include>
	<div class="container">
		<div class="control-group">
			<!-- 로그인 오류 메시지 -->
			<c:if test="${msg != null || msg.size() > 0}">
				<div class="alert alert-error">${msg}</div>
			</c:if>
		</div>
		<div class="control-group">
			<c:choose>
				<c:when test="${fbid != null}">
					<div class="form-group">
						<a href="login?fbop=logout" class="btn btn-mini btn-danger">로그아웃</a>
					</div>
				</c:when>
				<c:otherwise>
					<form class="form-horizontal" action="FBAuthServlet.do" method="post">
						<div class="form-group">
							<label for="userid" class="col-lg-2 control-label">ID</label>
							<div class="col-lg-3">
								<input type="text" class="form-control" id="userid" name="userid" 
									placeholder="ID" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="pwd" class="col-lg-2 control-label">Password</label>
							<div class="col-lg-3">
								<input type="password" class="form-control" id="pwd" name="pwd"
									 placeholder="Password" required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<button type="submit" value="login"class="btn btn-default">Login</button>
							</div>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<jsp:include page="./share/footer.jsp"></jsp:include>
</body>
</html>