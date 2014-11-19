<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link href="./login.css" rel ="stylesheet">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="//connect.facebook.net.en_US/all.js"></script>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>
					<div class="commonLogin">
						<form name="LoginForm" method="get">&nbsp;<label for="id">아이디</label> 
							<input type="text" name="id" required="required" tabindex="1">
							<p/>
							<label for="password">비밀번호</label>
							<input type="password" name="password" required="required" tabindex="2">&nbsp;&nbsp;
						</form>
						<p/><p/>
						<a href="#"><img src="../images/loginButton.jpg"></a>
						<!-- <input type="submit" value="로그인" id="auth" tabindex="3"> -->
						<p/><p/>
						<a href="register.jsp">회원가입</a>&nbsp;&nbsp;
						<a href="#">아이디 찾기</a>&nbsp;&nbsp;
						<a href="#">비밀번호 찾기</a>
						<!-- <a href="register.jsp"><input type="button" value="회원가입" id="register" tabindex="4"></a> -->
					
					</div>
				</td>
				<td><!-- SNS 로그인 연동 -->
					<div class="snsLogin">
							<a class="fb_buttonfb_button_large" id ="btnLogin" href="FBAutoServlet.do" data-size="xlarge">
							<span class ="fb_button_text">Log In</span>
							</a>
							<p/><p/>
						<a href="#"><img src="../images/twitter.jpg"></a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
