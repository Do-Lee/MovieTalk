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
</head>
<body>
	<jsp:include page="./share/header.jsp"></jsp:include>

	<div class="container">
		<form class="form-horizontal" method="POST" action="UserServlet.do">
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
					<input type="password" class="form-control" id="password" name="password"
                        placeholder="Password" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="pwdConfi" class="col-lg-2 control-label">Password Confirmation</label>
				<div class="col-lg-3">
					<input type="password" class="form-control" id="pwd_confirm" name="pwd_confirm"
						placeholder="Password Confirmation" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="nickname" class="col-lg-2 control-label">Nickname</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" id="nickname" name="nickname"
						placeholder="Nickname" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-lg-2 control-label">E-Mail</label>
				<div class="col-lg-3">
					<input type="email" class="form-control" id="email" name="email"
						placeholder="example@email.com" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="./share/footer.jsp"></jsp:include>
</body>
</html>