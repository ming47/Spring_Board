<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>

	<table border="1" align="center">
		<tr>
			<th>글 번호</th>
			<td>${board.seq}</td>
		</tr>

		<tr>
			<th>작성자</th>
			<td>${board.writer}</td>
		</tr>

		<tr>
			<th>작성일</th>
			<td>${board.write_date}</td>
		</tr>

		<tr>
			<th>제목</th>
			<td>${board.title}</td>
		</tr>

		<tr>
			<th>첨부된 파일</th>
			<td><button id="list">파일 목록</button>
				<fieldset id="filelist" style="display:none;">
					<legend>File List</legend>
				</fieldset></td>
		</tr>

		<tr>
			<th>내용</th>
			<td>${board.contents}</td>
		</tr>

	</table>

	<button id="back">Back</button>
	<script>
		$("#list").on("click",function() {
			$.ajax({
				url : "/files/list",
				data : { parent_seq : "${board.seq}"}
			}).done(function(resp) {
				console.log(resp);
				let ul = $("<ul>");
				for (let i = 0; i < resp.length; i++) {
				let a = $("<a>").attr("href","/files/download?sysName="
													+ resp[i].sysName
													+ "&oriName="
													+ resp[i].oriName).text(resp[i].oriName);
				let li = $("<li>").append(a);
				ul.append(li);
				}
				$("#filelist").append(ul).show();
				$("#list").hide();
				});
		});

		$("#back").on("click", function() {
			location.href = "/";
		});
	</script>

	<button id="update" type="button">수정하기</button>
	<button id="delete" type="button">삭제하기</button>

	<script>
		$("#delete").on("click", function() {
			let result = confirm("정말 삭제하시겠습니까?");
			if (result) {
				let seq = $
				{
					board.seq
				}
				;
				location.href = "/board/delete?seq=" + seq;
			}
		});
		$("#update").on("click", function() {
			location.href = "/";
		});
	</script>
	
		<table border="1" align="center">
		<form action="/reply/insert" method="post">
			<tr>
				<th>작성자</th>
				<td>${loginId}</td>
			</tr>

			<tr>
				<td><textarea name="contents" class="contents" placeholder="댓글을 입력하세요."></textarea></td>
				<td><button id="reply">등록</button></td>
			</tr>
			
			<tr id=newReply></tr>
		</form>
	</table>
	<script>
	$("#reply").on("click", function() {
		location.href = "/reply/toinsert";
	});
		
	</script>

</body>
</html>