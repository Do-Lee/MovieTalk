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
<script src="//code.jquery.com/jquery-lastest"></script>
<!-- Latest compiled and minified JavaScript -->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="./share/header.jsp"></jsp:include>

	<div class="container">
		<form class="form-horizontal" method="POST">
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
				<label for="pwdConfi" class="col-lg-2 control-label">Password Confirmation</label>
				<div class="col-lg-3">
					<input type="password" class="form-control" id="pwdConfi"
						placeholder="Password Confirmation">
				</div>
			</div>
			<div class="form-group">
				<label for="nickname" class="col-lg-2 control-label">Nickname</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" id="nickname"
						placeholder="Nickname">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-lg-2 control-label">E-Mail</label>
				<div class="col-lg-3">
					<input type="email" class="form-control" id="email"
						placeholder="example@email.com">
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