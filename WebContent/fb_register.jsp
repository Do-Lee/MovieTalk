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

	<div class="container">
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
					<div class="form-group">
						<label for="userid" class="col-lg-2 control-label">ID</label>
						<div class="col-lg-3">	
							<input type="text" class="form-control" id="userid" name="userid" placeholder="ID" 
								required="required">
						</div>
					</div>

					<input type="hidden" name="fbid" value="${me.id}">
					<input type="hidden" name="name" value="${me.name}">
					<c:if test="${method == 'POST'}">
						<%-- 신규 가입일 때만 비밀번호 입력창을 나타냄 --%>
						<div class="form-group">
							<label for="pwd" class="col-lg-2 control-label">Password</label>
							<div class="col-lg-3">
								<input type="password" class="form-control" id="pwd" name="pwd" 
									placeholder="Password" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="pwdConfi" class="col-lg-2 control-label">Password Confirmation</label>
							<div class="col-lg-3">
								<input type="password" class="form-control" id="pwd_confirm" name="pwd_confirm" 
									placeholder="Password Confirmation" required="required">
							</div>
						</div>
					</c:if>

					<div class="form-action">
						<c:choose>
							<c:when test="${method=='POST'}">
								<div class="form-group">
									<div class="col-lg-offset-2 col-lg-10">
										<button type="submit" class="btn btn-default">Submit</button>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="form-group">
									<div class="col-lg-offset-2 col-lg-10">
										<button type="submit" class="btn btn-primary">Modify</button>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</fieldset>
			</div>
		</form>
	</div>
	<jsp:include page="./share/footer.jsp"></jsp:include>
</body>
</html>