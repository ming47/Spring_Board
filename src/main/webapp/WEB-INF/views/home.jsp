<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>Home</title>
</head>

<body>

	<!-- 로그인 성공 -->
	<c:choose>
		<c:when test="${loginId !=null}">
			<table border="1" align="center">
				
				<tr>
					<th colspan="4">${loginId} 님 환영합니다.</th>
				</tr>
				<tr>
					<td colspan="4" align="center"><img src="" id="img" style="width: 150px; height: 150px; object-fit: cover;"></td>
				</tr>
				<tr>
					<td>
						<button id="mypage">마이페이지</button>
					</td>
					<td align="center">
						<button id="board">자유게시판</button>
					</td>
					<td align="center">
						<button id="logout">로그아웃</button>
					</td>
					<td align="center">
						<button id="memberout">회원탈퇴</button>
					</td>
				</tr>
			</table>
			<script>
				// 프로필 이미지를 AJAX로 불러옴
				$.ajax({
					url:"/members/getprofile"
				}).done(function(resp){
					console.log(resp);
					if(resp) {
						let imgPath = "/upload/" + resp;
						$("#img").attr("src",imgPath);
					}

				});
				
				$("#logout").on("click", function() {
					location.href = "/members/logout"
				});
				$("#memberout").on("click", function() {
					if (confirm("정말 탈퇴하시겠습니까?") == true) {
						//삭제요청한 ID를 세션에서 꺼낸후 request요청
						location.href = "/members/memberout?id=${loginId}";
					} else {
						location.href = "/";
					}
				});
				$("#mypage").on("click", function() {
					location.href = "/members/tomypage";
				});
				$("#board").on("click",function(){
					location.href = "/board/tolist";
				})
			</script>
		</c:when>

		<c:otherwise>
			<!-- 로그인 창 -->
			<form action="/members/login" method="post">
				<table border="1" align="center">
					<tr>
						<td><input type="text" placeholder="아이디" name="id" required></td>
					</tr>
					<tr>
						<td><input type="password" placeholder="비밀번호" name="pw"
							required></td>
					</tr>
					<tr>
						<td>
							<button>Login</button> <a href="/members/signupform"><button
									type="button">Signup</button></a>
						</td>
					</tr>
				</table>
			</form>
		</c:otherwise>
	</c:choose>

</body>
</html>