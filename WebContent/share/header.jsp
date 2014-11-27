<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
<!-- member bar -->
<div class="navbar navbar-default">
	<div class="nav nav-pills">
		<div class="container">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="./administrator.jsp">관리자 페이지</a></li>
				<li><a href="./register.jsp">회원가입</a></li>
				<li><a href="#none" onClick="location.href='./login.jsp?returnUrl=' + encodeURIComponent(location)">로그인</a></li>
				<li><a href="#none" onClick="FBLogout();" id = "logout">로그아웃</a></li>
				<li><fb:login-button id="login" scope="public_profile,user_friends" onlogin="checkLoginState();"></fb:login-button></li>
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
				<input type="text" class="form-control" id = "data">
				<button type="submit" class="btn btn-default">Search</button>
				<img src="images/ajax-loader.gif" style="display:none;" id="loading">
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

<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1500964636829366',
      xfbml      : true,
      version    : 'v2.2'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));

// Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) return;
	js = d.createElement(s); js.id = id;
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
function init(){
	$('#login').show();
	$('#logout').hide();
}

function fill(name) {
	// 아이템이 선택되었을때 처리 
	$('#data').val(name);
	$('#suggest_box').fadeOut();
}

$(function() {
	$('#search').keyup(function() {
		// 입력창에 키가 눌러진 경우 이벤트 처리
		// Ajax로 값을 전송
		$.post('DataSuggestServlet', {query: $('#data').val()}, 
			function(data) {
				$('#suggest_box').html(data).show();
			}
		);
	});
	// Ajax 진행 중임을 표시
	$('#loading').ajaxStart(function() {$(this).show();});
	$('#loading').ajaxComplete(function() {$(this).hide();});
});
</script>