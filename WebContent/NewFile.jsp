<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="">
		<select class="form-control" name="searchType">
			<option value="all">전체</option>
			<option value="subject">제목</option>
			<option value="content">내용</option>
		</select> <input type="text" name="searchWord" />
		<button class="btn btn-secondary btn-xs">검색</button>
	</form>
</body>
</html>