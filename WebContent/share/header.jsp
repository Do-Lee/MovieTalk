<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<link rel="stylesheet" href="share/header.css">
<div class="header">
	<div class="loginPart">
		<a href="register.jsp">회원가입</a> | 
		<a href="#none" onClick="location.href='share/login.jsp?returnUrl=' + encodeURIComponent(location)">로그인</a> 
	</div>
	<div class="title">
		<form action="form-horizontal" method="post" class="head">
			<span class="bold">Movie Talk</span>&nbsp;&nbsp;
			<input type="text" class="search">
			<input type="submit" value="Search" class="button">
		</form>
	</div>
	<div class="navbar">
		<a href="#">최신 영화</a>
		<a href="#">Top 10</a>
	</div>
</div>