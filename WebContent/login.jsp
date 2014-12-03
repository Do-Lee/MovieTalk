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
<script src="//connect.facebook.net/en_US/all.js"></script>
</head>
<body>
	<jsp:include page="./share/header.jsp"></jsp:include>

	<div class="container">
		<form class="form-horizontal" method="post" action="LoginController.do">
			<div class="form-group">
				<label for="userid" class="col-lg-2 control-label">ID</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" id="userid" name="userid"
						placeholder="ID" required="required">
				</div>
			</div>
			<div class="form-group">
				<label for="pwd" class="col-lg-2 control-label">Password</label>
				<div class="col-lg-3">
					<input type="password" class="form-control" id="pwd" name="pwd"
						placeholder="Password" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<div class="btn-group btn-group-xs">
						<a href="register.jsp" class="btn btn-default">회원가입</a> 
                        <a href="#" class="btn btn-default">아이디 찾기</a> 
                        <a href="#" class="btn btn-default">비밀번호 찾기</a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default">Login</button>
                    &nbsp;&nbsp;&nbsp;
                    
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
                    <fb:login-button id="login" scope="public_profile,user_friends"
                        onlogin="checkLoginState();"></fb:login-button>
                      <!-- <a class="fb_button fb_button_large" id="btnLogin"
                        href="FBAuthServlet.do" data-size="xlarge"> <img
                        src="./images/facebook.jpg">
                      </a> <a href="#"><img src="./images/twitter.jpg"></a> -->
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="./share/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	window.fbAsyncInit = function() {
		FB.init({
			appId : '1500964636829366',
			xfbml : true,
			version : 'v2.2'
		});
	};

	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) {
			return;
		}
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	// Load the SDK asynchronously
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	function statusChangeCallback(response) {
		if (response.status === 'connected') {
			$('#login').hide();
			$('#logout').show();
		} else if (response.status === 'not_authorized') {
			init();
		} else {
			init();
		}
	}
	// This function is called when someone finishes with the Login Button.
	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}
	function FBLogout() {
		FB.logout(function(response) {
			$('#login').show();
			$('#logout').hide();
		});
		
	}
	function init() {
		$('#login').show();
		$('#logout').hide();
	}
</script>
</html>



