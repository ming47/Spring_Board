<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	<form action="/members/insert" method="post" enctype="multipart/form-data">
		<table border="1" align="center">
			<tr>
				<th>프로필 이미지</th>
				<td>
				<input type="file" name="files" id="imageInput" accept="image/*">
				<img src="" id="image" width="120px" height="120px">
				</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
				<input type="text" id="id" placeholder="아이디" name="id">
				<button id="idcheck" type="button">중복확인</button>
				<div id ="msg"></div>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" placeholder="비밀번호" name="pw"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" placeholder="이름" name="name"></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type="text" placeholder="연락처" name="contact"></td>
			</tr>
			<tr>
				<td>
				<input type="submit" value="가입완료">
				<input type="button" value="취소">
				</td>
			</tr>
		</table>
	
	<script>
		// 프로필 사진 선택
		$("#imageInput").on("change",function(){
			const file=this.files[0];
			if(file){
				if(file.type.startsWith('image/')){
					$("#image").attr("src",URL.createObjectURL(file));
				}
			}
		})
	
		// 아이디 중복 체크
		$("#idcheck").on("click",function(){
			$.ajax({
				url:"/members/idcheck",
				data:{id:$("#id").val()}
			}).done(function(resp) {
				
				if(JSON.parse(resp)){
					$("#msg").css("color","red")
					$("#msg").html("이미 사용중인 ID 입니다.")
				}else{
					$("#msg").css("color","blue")
					$("#msg").html("사용 가능한 ID 입니다.")
				};
									
			});
		});
		
	</script>
	</form>
</body>
</html>