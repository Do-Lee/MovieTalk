<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="user.User" />
<%//int id = (int) session.getAttribute("id"); %>
<%String userid = (String) session.getAttribute("userid"); %>
<%String name = (String) session.getAttribute("name"); %>
<link rel="stylesheet" href="./css/autocomplete.css">
<script src="http://code.jquery.com/jquery-1.11.0.js"></script>

<!-- member bar -->
<div class="navbar navbar-default">
	<div class="nav nav-pills" style="background: #fbfbfb;">
		<div class="container">
			<ul class="nav navbar-nav navbar-right">
            	<% if(userid == null) { %>
                <li><a href="./register.jsp">회원가입</a></li>
				<li><a href="login.jsp">로그인</a></li>
           		<% } else { %>
                <li><a href='<%="user?op=update&id=" %>'><%=name %></a></li>
                <% if(userid.equals(user.getAdmin())) { %>
                	<li><a href="user?op=admin" >회원 관리</a></li>
					<li><a href="fbuser?op=admin">페이스북 회원 관리</a></li>
					<li><a href="movie?op=admin">채팅 관리</a></li>
          		<% } else { %>
                	<li><a href="chat?op=mine">회원 페이지</a></li>
               	<% } %>
				<li><a href="user?op=logout" onClick="FBLogout();">로그아웃</a></li>
              	<% } %>
			</ul>
		</div>
	</div>
</div>

<!-- search bar -->
<div class="container" style="padding-top: 30px;" align="center">
	<form class="form-inline" method="GET" action="movie">
		<input type="hidden" name="op" value="search">
		<h1><a href="./index.jsp">Movie Talk</a></h1>
		<div class="form-group">
			<input type="text" class="form-control" id="data" name="query" style="width: 300px;" required="required">
			<img src="images/ajax-loader.gif" style="display:none;" id="loading">
			<div class="suggest_box" id="suggest_box"></div> 
			<button type="submit" class="btn btn-default">Search</button>
			<img src="images/ajax-loader.gif" style="display: none;" id="loading">
		</div>
	</form>
</div>

<!-- menu bar -->
<div class="navbar navbar-default" style="margin-top: 30px; background:#939393;">
	<div class="nav nav-pills"></div>
</div>


<script type="text/javascript">
function fill(name) {
	// 아이템이 선택되었을때 처리 
	$('#data').val(name);
	$('#suggest_box').fadeOut();
}

$(function() {
	$('#data').keyup(function() {
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