<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 게시판</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

</head>
<body>

	<table border="1" align="center">
		<tr>
			<th>no</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회</th>
		</tr>
	</table>

	<button id="write">작성하기</button>

	<script>
   $(function() { // 게시글 목록
       $.ajax({
           url: "/board/list"
       }).done(function(resp) {
          console.log(resp);
           
           for(let i = 0; i < resp.length; i++) {
              let dto = resp[i];
              let tr = $('<tr>');
              
           	  // 제목에 링크 추가
              let titleLink = $('<a>').attr('href', '/board/detail?seq=' + dto.seq).text(dto.title);
              tr.append($('<td>').html(dto.seq));
              tr.append($('<td>').html(titleLink));
              tr.append($('<td>').html(dto.writer));
              tr.append($('<td>').html(dto.write_date));
              tr.append($('<td>').html(dto.view_count));
              $('table').append(tr);
           }
       });
   })
   $("#write").on("click",function(){
	   location.href="/board/towrite"
   })
   </script>

</body>
</html>