<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<form class="form-horizontal" method="post" action="LoginController.do">
			<div class="form-group">
				<label for="userid" class="col-lg-2 control-label">ID</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" id="userid" name="userid"
						placeholder="ID" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="pwd" class="col-lg-2 control-label">Password</label>
				<div class="col-lg-3">
					<input type="password" class="form-control" id="pwd" name="pwd"
						placeholder="Password" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<div class="btn-group btn-group-xs">
						<a href="register.jsp" class="btn btn-default">회원가입</a> 
                        <a href="#" class="btn btn-default">아이디 찾기</a> 
                        <a href="#" class="btn btn-default">비밀번호 찾기</a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default">Login</button>
                    &nbsp;&nbsp;&nbsp;
                    
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<a class="fb_button fb_button_large" id="btnLogin" href="FBAuthServlet.do" data-size="xlarge"> 
						<span class="fb_button_text">FaceBook LogIn</span>
					</a>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="./share/footer.jsp"></jsp:include>
</body>
</html>



