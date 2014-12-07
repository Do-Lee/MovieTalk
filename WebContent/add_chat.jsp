<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

		<form class="form-horizontal" method="POST" action="movie?op=create">
            <input type="hidden" name="_method" value="createChat">
			<div class="form-group">
				<label for="movietitle" class="col-lg-2 control-label">Movie Title</label>
				<div class="col-lg-3">
    				<input type="text" class="form-control" id="data" name="movietitle"
    						placeholder="Movie Title" style="width: 300px;">
                    <img src="images/ajax-loader.gif" style="display:none;" id="loading">
                    <div class="suggest_box" id="suggest_box"></div>
				</div>
			</div>
			<div class="form-group">
				<label for="title" class="col-lg-2 control-label">Title</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" name="title"
						placeholder="Title" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="writer" class="col-lg-2 control-label">Writer</label>
				<div class="col-lg-3">
					<input type="text" class="form-control" name="name" value=<%=session.getAttribute("name") %>
						readonly="readonly" style="width: 300px;">
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="col-lg-2 control-label">Content</label>
				<div class="col-lg-3">
					<textarea class="form-control" rows="10" name="contents" style="width: 300px;"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-1" align="left">
					<button type="submit" class="btn btn-default">개설</button>
				</div>
                <img src="images/ajax-loader.gif" style="display:none;" id="loading">
			</div>
		</form>

	</div>

	<jsp:include page="share/footer.jsp"></jsp:include>
</body>
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
</html>