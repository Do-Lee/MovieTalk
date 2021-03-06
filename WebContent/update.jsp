<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    <jsp:include page="./share/header.jsp"></jsp:include>

	<div class="container">
		<form class="form-horizontal" method="POST" action="user">
            <input type="hidden" name="_method" value="update">
			<div class="form-group">
				<label for="userid" class="col-lg-2 control-label">ID</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" name="userid" value="${user.userid}"
						placeholder="ID" readonly="readonly" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="pwd" class="col-lg-2 control-label">Password</label>
				<div class="col-lg-3">
					<input type="password" class="form-control" name="password"
                        placeholder="Password" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="pwdConfirm" class="col-lg-2 control-label">Password Confirmation</label>
				<div class="col-lg-3">
					<input type="password" class="form-control" name="pwd_confirm"
						placeholder="Password Confirmation" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="nickname" class="col-lg-2 control-label">Nickname</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" name="nickname" value="${user.name}"
						placeholder="Nickname" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="email" class="col-lg-2 control-label">E-Mail</label>
				<div class="col-lg-3">
					<input type="email" class="form-control" name="email" value="${user.email}"
						placeholder="example@email.com" required="required" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default">수정</button>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="./share/footer.jsp"></jsp:include>
</body>
</html>