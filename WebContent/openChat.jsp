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
	<jsp:include page="share/header.jsp"></jsp:include>

	<div class="container" align="center">

		<form class="form-horizontal" method="POST">

			<div class="form-group">
				<label for="movietitle" class="col-lg-2 control-label">Movie Title</label>
				<div class="col-lg-3">
				<input type="text" class="form-control" id="movietitle"
						placeholder="Movie Title">
				</div>
			</div>

			<div class="form-group">
				<label for="title" class="col-lg-2 control-label">Title</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" id="title"
						placeholder="Title">
				</div>
			</div>
			<div class="form-group">
				<label for="writer" class="col-lg-2 control-label">Writer</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" id="nickname"
						placeholder="Nickname">
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="col-lg-2 control-label">Content</label>
				<div class="col-lg-3">
					<textarea class="form-control" rows="10"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-1" align="left">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form>

	</div>

	<jsp:include page="share/footer.jsp"></jsp:include>
</body>
</html>