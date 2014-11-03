<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<% 
	String id = (String)request.getParameter("id");
	String password = (String)request.getParameter("password");
%>

<link rel="stylesheet" href="login.css">

<script type="text/javascript" src="http://connect.facebook.net/ko_KR/all.js"></script>
<script type="text/javascript">
	window.fbAsyncInit = function() {
		FB.init({
			appId: "1501891036726846",	// temporary(?) facebook App ID
			status: true, 							// check login status
			cookie: true,							  // enable cookies to allow the server to access the session
			xfbml: true, 								// parse XFBML
			oauth: true									// enable OAuth 2.0
		});
	};
	
	(function(d) {
		var js, id = 'facebook-jssdk', ref = d.getElementsByTagName("script")[0];
		if(d.getElementById(id)) { return ; }
		js = d.createElement("script"); js.id = id; js.async = true;
		js.src = "//connect.facebook.net/en_US/all.js";
		ref.parentNode.insertBefore(js, ref);
	}(document));
	
	FB.login(function(response) {
		if(response.authResponse) {
			console.log('Welcome! Fetch your information');
			FB.api('/me', function(response) {
				console.log('Good to see you ' + response.name + '.');
			});
		} else {
			console.log('User cancelled login or did not fully authorize.');
		}
	});
	
	function getMyProfile() {
		FB.api('/me', function(user) {
			var name = user.name;
			var email = user.email;
			var id = user.id;
		});
	}
</script>

<div>
	<table>
		<tr>
			<td>
				<div class="commonLogin">
					<form name="LoginForm" method="get">
						&nbsp;<label for="id">아이디</label> 
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
					<a href="#"><img src="../images/facebook.jpg"></a>
					<p/><p/>
					<a href="#"><img src="../images/twitter.jpg"></a>
				</div>
			</td>
		</tr>
	</table>
</div>