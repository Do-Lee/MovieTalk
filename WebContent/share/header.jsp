<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <jsp:useBean id="user" class="user.User"/>
<%String userid = (String)session.getAttribute("id"); %>
<%String pwd = (String)session.getAttribute("password"); %>
<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
<!-- member bar -->
<div class="navbar navbar-default">
	<div class="nav nav-pills">
		<div class="container">
			<ul class="nav navbar-nav navbar-right">
               
               <%if(userid != null) {%>
                   <%
                     if(userid.equals(user.getAdmin())) {
                   %>
                  <li><a href="./administrator.jsp">관리자 페이지</a></li>
                  <%} else {%>
				  <li><a href="./member.jsp">회원 페이지</a></li>
                 <%} 
                 }%>
				<li><a href="./register.jsp">회원가입</a></li>
               
               <%
                 if(session.getAttribute("id") == null) {
               %>
				<li><a href="login.jsp">로그인</a></li>
               <%
                 } else {
               %>
                <li>ID: <%=userid %></li>
				<li><a href="LogoutController.do" onClick="FBLogout(); alert('로그아웃되었습니다.');" id="logout">로그아웃</a></li>
               <%} %>
			</ul>
		</div>
	</div>
</div>

<!-- search bar -->
<div class="container" style="padding-top: 50px" align="center">
	<form class="form-inline" method="POST" action="MovieServlet.do">
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
		</div>
	</div>
</div>

<script>
 
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