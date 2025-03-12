<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>My Page</title>
<script>
	$(function(){
		$.ajax({
			url:"/members/mypage",
	    	type: "GET"  
		}).done(function(resp){
			console.log(resp);
			$("#id").text(resp.id);
			$("#name").text(resp.name);
			$("#contact").text(resp.contact);
		});
	})
</script>
</head>

<body>

	<table border="1" align="center">
		<tr>
			<th>ID</th>
			<th>NAME</th>
			<th>Contact</th>
		</tr>

		<tr>
			<td id="id"></td>
			<td id="name"></td>
			<td id="contact"></td>
		</tr>
	</table>
	
	<button id="update">수정</button>
	<button id="back">Back</button>
	
	    <script>
		$("#update").on("click", function() {
    		alert("수정하기클릭!");
    		
    	});
		$("#back").on("click", function() {
			location.href = "/";
    	});
			
    </script>
</body>
</html>