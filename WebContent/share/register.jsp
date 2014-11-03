<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<link rel="stylesheet" href="register.css">
<!-- temp!!!! -->
<form name="registerForm">
	<label for="nickname">닉네임&nbsp;&nbsp;&nbsp;&nbsp;</label>
	<input type="text" name="nickname" required="required">
	<input type="button" name="checkNickname" value="중복확인" required="required"><p/>
	<label for="id">아이디&nbsp;&nbsp;&nbsp;&nbsp;</label>
	<input type="text" name="id" required="required">
	<!-- DB 구축 후, 버튼 지울 수 있으면 지우기.. -->
	<input type="button" name="checkID" value="중복확인" required="required"><p/>
	
	<label for="password">비밀번호</label>
	<input type="password" name="password" required="required"><p/>
	<label for="email">이메일&nbsp;&nbsp;&nbsp;&nbsp;</label>
	<input type="email" name="email" required="required"> <p/>
	<input type="submit" class="register" name="register" value="등록">
</form>
