<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Talk</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- Latest compiled and JQuery -->
<script src="//code.jquery.com/jquery-latest.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//connect.facebook.net/en_US/all.js"></script>
</head>
<body>
	<jsp:include page="./share/header.jsp"></jsp:include>
	<div class="row-fluid">
		<div class="span4">...</div>
		<div class="span8 offset4">...</div>
	</div>

	<div class="container">
		<form class="form-horizontal">
			<div class="form-group">
				<label for="userid" class="col-lg-2 control-label">ID</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" id="userid"
						placeholder="ID">
				</div>
			</div>
			<div class="form-group">
				<label for="pwd" class="col-lg-2 control-label">Password</label>
				<div class="col-lg-3">
					<input type="password" class="form-control" id="pwd"
						placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<div class="btn-group btn-group-xs">
						<a href="register.jsp" class="btn btn-default">회원가입</a> <a
							href="#" class="btn btn-default">아이디 찾기</a> <a href="#"
							class="btn btn-default">비밀번호 찾기</a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default">Login</button>
				</div>
			</div>
			<<<<<<< Updated upstream =======
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<a class="fb_button fb_button_large" id="btnLogin"
						href="FBAuthServlet.do" data-size="xlarge"> <img
						src="./images/facebook.jpg">
					</a> <a href="#"><img src="./images/twitter.jpg"></a>
				</div>
			</div>
			>>>>>>> Stashed changes
		</form>
	</div>
	<jsp:include page="./share/footer.jsp"></jsp:include>
</body>

</html>



