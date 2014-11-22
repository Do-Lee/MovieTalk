<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- member bar -->
<div class="navbar navbar-default">
	<div class="nav nav-pills">
		<div class="container">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="./administrator.jsp">관리자 페이지</a></li>
				<li><a href="./register.jsp">회원가입</a></li>
				<li><a href="#none" onClick="location.href='./login.jsp?returnUrl=' + encodeURIComponent(location)">로그인</a></li>
			</ul>
		</div>
	</div>
</div>

<!-- search bar -->
<div class="container" style="padding-top: 50px" align="center">
	<form class="form-inline" method="POST">
		<div class="form-group">
			<h1>
				<a href="./index.jsp">Movie Talk</a>
				<input type="text" class="form-control">
				<button type="submit" class="btn btn-default">Search</button>
			</h1>
		</div>
	</form>
</div>

<!-- menu bar -->
<div class="navbar navbar-default" style="margin-top: 50px">
	<div class="nav nav-pills">
		<div class="container">
			<ul class="nav navbar-nav">
				<li><a href="#">최신 영화</a></li>
				<li><a href="#">Top 10</a></li>
			</ul>
		</div>
	</div>
</div>