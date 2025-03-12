<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성하기</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	<form action="/board/write" method="post" enctype="multipart/form-data">
		<input type="hidden" name="writer" value="${loginId}">
		<table border="1">
			<tr>
				<th>글 작성하기</th>
			</tr>

			<tr>
				<td><input type="text" name="title" placeholder="제목을 입력하세요"></td>
			</tr>
			<tr>
				<td><input type="file" name="files" multiple></td>
			</tr>
			<tr>
				<td><input type="text" name="contents" placeholder="내용을 입력하세요"></td>
			</tr>
			<tr>
				<td>
					<button>작성완료</button>
					<button type="button">목록으로</button>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>