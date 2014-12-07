<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="chat" class="chat.ChatDAO"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Talk</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/common.css">
<script src="./js/jquery-1.11.1.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="share/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail" style="height: 240px;">
					<div class="caption">
                      <%if ( chat.getHotMessage() == null ) {%>
                        <h4>Hot Topic</h4>
                      <%} else { %>
                        <img src="<%=chat.getHotMessage().getImage() %>">
						<h4 style="margin-top:8px 0 10px 0; text-align: center;"><%=chat.getHotMessage().getMovietitle() %></h4>
                        <p style="margin: 0px;"><a href="./chat.jsp?title=<%=chat.getHotMessage().getTitle() %>" >
                            <%=chat.getHotMessage().getTitle() %></a></p>
                      <%} %>
					</div>
				</div>
				<div class="thumbnail" style="height: 240px; float: bottom">
					<div class="caption">
                      <%if ( chat.getNewMessage() == null ) {%>
                        <h4>New Topic</h4>
                      <%} else { %>
                        <img src="<%=chat.getNewMessage().getImage() %>">
						<h4 style="margin-top:8px 0 10px 0; text-align: center;"><%=chat.getNewMessage().getMovietitle() %></h4>
                        <p style="margin: 0px;"><a href="./chat.jsp?title=<%=chat.getNewMessage().getTitle() %>" >
                            <%=chat.getNewMessage().getTitle() %></a></p>
                      <%} %>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<img src="./images/movie1.jpg">
					<div class="caption">
						<h4>Movie 1</h4>
						<p style="margin: 0px;"><a href="./chat.jsp" >test chat</a></p>
						<p style="margin: 0px;"><a href="./chat.jsp" >test chat</a></p>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<img src="./images/movie2.jpg">
					<div class="caption">
						<h4>Movie 2</h4>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<img src="./images/movie3.jpg">
					<div class="caption">
						<h4>Movie 3</h4>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<a href="./add_chat.jsp">
					<img src="./images/add_chat.jpg">
					</a>
					<div class="caption">
						<h4>Add Chatting</h4>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="share/footer.jsp"></jsp:include>
</body>
</html>