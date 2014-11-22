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
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail" style="max-height: 500px; overflow-y: auto">
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Movie Image</th>
									<th>Chat Title</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Image...</td>
									<td><span class="badge pull-right">42</span>Hello Chat
										World</td>
								</tr>
								<tr>
									<td>Image...</td>
									<td><span class="badge pull-right">28</span>Hello Chatting</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail" style="max-height: 500px; overflow-y: auto">
					<img src="./images/movie2.jpg" style="width: 200px; height: 290px">
					<div class="caption">
						<h3>Movie 2</h3>
						<p>Movie Information</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail" style="max-height: 500px; overflow-y: auto;">
					<p>...</p>
					<p>...</p>
					<p>...</p>

					<form method="POST">
						<div class="form-group">
							<div class="col-lg-9">
								<input type="text" class="form-control" id="meassage">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="share/footer.jsp"></jsp:include>
</body>
</html>