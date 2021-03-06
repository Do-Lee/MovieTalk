<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="movie.Movie" import="chat.Message"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="MovieDAO" class="movie.MovieDAO" scope="request"/>
<jsp:useBean id="ChatDAO" class="chat.ChatDAO" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Talk</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/common.css">
<link rel="stylesheet" href="./css/chat.css">
<script src="./js/jquery-1.11.1.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/json2.js"></script>

</head>
<body>
	<jsp:include page="share/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Movie Image</th>
									<th>Chat Title</th>
								</tr>
							</thead>
							<tbody>
                              <%for (Movie movie : MovieDAO.findAllMoviesByTitle(request.getParameter("movietitle"))) {%>
								<tr>
									<td><img src="<c:out value="<%=movie.getImage()%>"></c:out>"></td>
									<td>
										<a href="movie?movietitle=<%=movie.getMovietitle()%>&chattitle=<%=movie.getChattitle()%>"><%=movie.getChattitle()%></a>
										<span class="badge pull-right"><c:out value="<%=ChatDAO.findChatLastId(movie.getChattitle())%>"></c:out></span>
									</td>
								</tr>
                              <% } %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<% Movie movie = MovieDAO.findChat(request.getParameter("chattitle")); %>
					<img src="<c:out value="<%=movie.getImage()%>"></c:out>">
					<div class="caption">
						<h3 align="center"><%=movie.getMovietitle() %></h3>
						<p>Chat Title: <%=movie.getChattitle() %></p>
						<p>Wrtier: <%=movie.getOpener() %></p>
						<p>Contents: <%=movie.getContents() %></p>
					</div>
				</div>
			</div>
			
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
						<table>
							<tbody id="messages"></tbody>
						</table>
					<div id="error"></div>
					<form id="chat_form">
						<div class="form-group">
							<input type="hidden" class="form-control" id="title" value="${requestScope.chattitle }">
							<input type="text" class="form-control" id="message">
							<input type="button" class="btn btn-default" id="send" value="send" style="width: 100%; background: #eeeeee;"> 
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="share/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var last_id = -1; // unknown
	var timer = null;

	function receive() {
		// Ajax로 마지막 받은 번호 이후의 메시지를 json으로 받음.

		$.get('chat', {
			last : last_id,
			title : $("#title").val()
		}, function(data) {
			if (data.messages.length > 0 && last_id < data.last) {
				last_id = data.last;
				$(data.messages).each(function(i, item) {
					// 각 메시지를 해당위치에 추가
					if(item.mine.length > 0) {
						$("<tr>").
							append("<td></td>").
							append("<td class='message mine'>" + 
										"<span class='name'>" + item.writer + "</span>" + 
										"<span class='time'>" + item.time + "</span><br>" + 
										"<span class='content'>" + item.message + "</span>" + 
								  "</td>").
							append("</tr>").
							appendTo("#messages");
					} 
					else {
						$("<tr>").
						append("<td>" + 
									"<span class='name'>" + item.writer + "</span>" + 
									"<span class='time'>" + item.time + "</span><br>" + 
									"<span class='content'>" + item.message + "</span>" + 
							  "</td>").
						append("<td></td>").
						append("</tr>").
						appendTo("#messages");
					}
				});
				// 새로운 메시지가 있을 경우, 입력 폼이 보이도록 스크롤
				$(".thumbnail").animate({scrollTop : $("#chat_form").offset().top}, 1000);
			}
		});
	}
	$(function() {
		$("#send").click(function() {
			// 이름이나 내용이 없으면 포커스를 옮기고 종료
			<% if ((String) session.getAttribute("name") == null) { %>
				alert("로그인 해주세요.");
				location.replace("./login.jsp");
				return;
			<% } %>

			// Ajax로 글 내용 전달
			$.post('chat', {
				content : $("#message").val(),
				title : $("#title").val()
			}, function(data) {
				if (data.indexOf("ERROR") != -1) {
					// 에러가 있으면 내용 출력
					$("#error").text(data).fadeIn();
				} 
				else {
					$("#error").fadeOut();
				}
			});

			// 글을 쓴 후에는 메시지창의 내용을 없앰.
			$("#message").val("");
		});

		// 메시지 창에서 Enter를 누르면 SEND버튼을 누르도록.
		$('#message').keydown(function(event) {
			if (event.keyCode == 13)
				$("#send").click();
		});

		// Ajax 진행 중임을 표시
		$('#loading').ajaxStart(function() {$(this).show();});
		$('#loading').ajaxComplete(function() {$(this).hide();});

		receive();

		// 1초마다 새로운 메시지를 받도록 지정
		setInterval(receive, 1000);

	});
</script>
</html>