<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<style type="text/css">
.header {
	width: 100%;
	max-width: 1120px;
	height: 100%;
	margin: 0 auto;
}
.title {
	margin: 0 auto;
	margin-top: 88px;
	
}
.title input {
	margin-left: 30px;
	height: 35px;
}
.title input.search {
	width: 354px;
}
.title input.button {
	padding: 10 10 10 10;
	width: 93px;
}

.navbar {
	position: 0 176px;
	margin-top: 53px;
	width: 100%;
	height: 35px;
	background-color: #888888;
}
.navbar a {
	margin-left: 35px;
	padding: 10px 5px 10px 5px;
	color: #FFF;
	text-decoration: none;
	float: left;
}

span.bold {
	padding-top: 5px;
	font-size: 20px;
	padding-bottom: 5px;
}
</style>

<div class="header">
	<div class="title">
		<form action="form-horizontal" method="post">
			<span class="bold">Movie Talk</span>
			<input type="text" class="search">
			<input type="submit" class="button">
		</form>
	</div>
	<div class="navbar">
		<a href="#">최신 영화</a>
		<a href="#">Top 10</a>
	</div>
</div>