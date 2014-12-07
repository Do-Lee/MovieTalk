<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="chat.Message" pageEncoding="UTF-8"%>
<jsp:useBean id="ChatDAO" class="chat.ChatDAO"/>
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
                              <%for (Message message : ChatDAO.findAllChatsByTitle((String)request.getAttribute("title"))) {%>
								<tr>
									<td><%=message.getImage()%></td>
									<td><span class="badge pull-right">42</span><%=message.getTitle()%></td>
								</tr>
                              <%} %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
					<img src="${msg.image}">
					<div class="caption">
						<h3>${msg.movietitle}</h3>
						<p>${msg.title}</p>
						<p>${msg.userid}</p>
						<p>${msg.content}</p>
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
			last : last_id
		}, function(data) {
			if (data.messages.length > 0 && last_id < data.last) {
				last_id = data.last;
				$(data.messages).each(function(i, item) {
					// 각 메시지를 해당위치에 추가
					if(item.mine.length > 0) {
						$("<tr>").
							append("<td></td>").
							append("<td class='message mine'>" + 
										"<span class='name'>" + item.userid + "</span>" + 
										"<span class='time'>" + item.time + "</span><br>" + 
										"<span class='content'>" + item.message + "</span>" + 
								  "</td>").
							append("</tr>").
							appendTo("#messages");
					} 
					else {
						$("<tr>").
						append("<td>" + 
									"<span class='name'>" + item.userid + "</span>" + 
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
			if ($("#message").val().length == 0) {
				alert("내용을 입력하여 주세요.");
				$("#message").focus();
				return;
			}

			// Ajax로 글 내용 전달
			$.post('chat', {
				content : $("#message").val()
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