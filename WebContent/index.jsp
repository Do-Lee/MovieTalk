<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="movie.Movie" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="MovieDAO" class="movie.MovieDAO" />
<jsp:useBean id="ChatDAO" class="chat.ChatDAO" />
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
						<h4>Hot Topic</h4>
						<% for (Movie movie : MovieDAO.findHotChats(5)) { %>
						<p style="margin: 0px;">
							<a
								href="movie?movietitle=<%=movie.getMovietitle()%>&chattitle=<%=movie.getChattitle()%>">
								<%=movie.getChattitle() %></a> <span class="badge pull-right"><c:out
									value="<%=ChatDAO.findChatLastId(movie.getChattitle())%>"></c:out></span>
						</p>
						<% } %>
					</div>
				</div>
				<div class="thumbnail" style="height: 240px; float: bottom">
					<div class="caption">
						<h4>New Topic</h4>
						<% for (Movie movie : MovieDAO.findNewChats(5)) { %>
						<p style="margin: 0px;">
							<a
								href="movie?movietitle=<%=movie.getMovietitle()%>&chattitle=<%=movie.getChattitle()%>">
								<%=movie.getChattitle() %></a> <span class="badge pull-right"><c:out
									value="<%=ChatDAO.findChatLastId(movie.getChattitle())%>"></c:out></span>
						</p>
						<% } %>
					</div>
				</div>
			</div>
			<% for (Movie chatList : MovieDAO.findMovies(3)) {%>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<img src="<c:out value="<%=chatList.getImage()%>"></c:out>">
					<div class="caption">
						<h4>
							<c:out value="<%=chatList.getMovietitle()%>"></c:out>
						</h4>

						<% for (Movie movie : MovieDAO.findAllMoviesByTitle(chatList.getMovietitle())) { %>
						<p style="margin: 0px;">
							<a
								href="movie?movietitle=<%=movie.getMovietitle()%>&chattitle=<%=movie.getChattitle()%>"><%=movie.getChattitle()%></a>
						</p>
						<% } %>
					</div>
				</div>
			</div>
			<% } %>
			<div class="col-sm-6 col-md-2">
				<div class="thumbnail">
					<a href="./add_chat.jsp"> <img src="./images/add_chat.jpg">
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