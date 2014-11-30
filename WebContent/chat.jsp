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
<script src="./js/json2.js"></script>
</head>
<body>
	<jsp:include page="share/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail" style="max-height: 500px; overflow-y: auto">
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Movie Image</th>
									<th>Chat Title</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Image...</td>
									<td><span class="badge pull-right">42</span>Hello Chat
										World</td>
								</tr>
								<tr>
									<td>Image...</td>
									<td><span class="badge pull-right">28</span>Hello Chatting</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail" style="max-height: 500px; overflow-y: auto">
					<img src="./images/movie2.jpg" style="width: 200px; height: 290px">
					<div class="caption">
						<h3>Movie 2</h3>
						<p>Movie Information</p>
						<p>...</p>
						<p>...</p>
						<p>...</p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail" style="max-height: 500px; overflow-y: auto;">
					<!-- 
					<form method="POST" id="chat_form">
						<div class="form-group">
							<div class="col-lg-9">
								<input id="message" type="text" size="50"> 
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
					</form>-->
					<div id="messages"></div>
					<div id="error" style="display: none"></div>
					<form id="chat_form">
						<input id="name" type="text" size="50"> 
						<input id="msg" type="text" size="50">
						<input id="send" type="button" value="send"> 
						<img src="images/ajax-loader.gif" style="display: none;" id="loading">
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

		$.get('ChatServlet', {
			last : last_id
		}, function(data) {
			if (data.messages.length > 0 && last_id < data.last) {
				last_id = data.last;
				$(data.messages).each(
						function(i, item) {

							// 각 메시지를 해당위치에 추가
							$("<div class='message " + item.mine + "'></div>")
									.append(
											"<span class='name'>" + item.namen
													+ "</span>").append(
											item.content).append(
											"<span class='time'>" + item.time
													+ "</span>").appendTo(
											"#messages");
						});
				// 새로운 메시지가 있을 경우, 입력 폼이 보이도록 스크롤
				$('html, body').animate({
					scrollTop : $("#chat_form").offset().top
				}, 1000);
			}
		});
	}
	$(function() {
		$("#send").click(function() {
			// 이름이나 내용이 없으면 포커스를 옮기고 종료
			if ($("#name").val().length == 0) {
				alert("이름을 입력하여 주세요.");
				$("#name").focus();
				return;
			}
			if ($("#message").val().length == 0) {
				alert("내용을 입력하여 주세요.");
				$("#message").focus();
				return;
			}

			// Ajax로 글 내용 전달
			$.post('ChatServlet', {
				name : $("#name").val(),
				content : $("#message").val()
			}, function(data) {
				if (data.indexOf("ERROR") != -1) {
					// 에러가 있으면 내용 출력
					$("#error").text(data).fadeIn();

				} else {
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
		$('#loading').ajaxStart(function() {
			$(this).show();
		});
		$('#loading').ajaxComplete(function() {
			$(this).hide();
		});

		receive();

		// 1초마다 새로운 메시지를 받도록 지정
		setInterval(receive, 1000);

	});
</script>
</html>