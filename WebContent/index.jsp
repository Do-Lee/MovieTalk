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
	<jsp:include page="share/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<div class="caption">
						<h3>Hot Topic</h3>
					</div>
				</div>
				<div class="thumbnail">
					<div class="caption">
						<h3>New Topic</h3>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<img src="./images/movie1.jpg">
					<div class="caption">
						<h3>Movie 1</h3>
						<p><a href="./chat.jsp">test chat</a></p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<img src="./images/movie2.jpg">
					<div class="caption">
						<h3>Movie 2</h3>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<img src="./images/movie3.jpg">
					<div class="caption">
						<h3>Movie 3</h3>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<a href="./openChat.jsp"><img src="./images/open_chat.jpg"></a>
					<div class="caption">
						<h3>Open Chatting</h3>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="share/footer.jsp"></jsp:include>
</body>
</html>