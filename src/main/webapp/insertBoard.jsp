<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.*"%>


<!--  지시어  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- view 페이지 : 클라이언트에게 보여지는 페이지 -->
	

		<h1> [ 게시물 등록 페이지 ] </h1>
		<hr>
		<!--form 태그안에 변수 값을 담아서 insertBoard.bb 호출   -->
		<form method="post" action="insertBoard.bb">
			<!-- bb 요청은 Board_Controller.java에서 받도록 설정   -->
			<table border="1" cellpadding="10" cellspacing="0">
				<!-- cellpadding : cell과 cell 사이의 간격  -->
				<tr>
					<td bgcolor="pink">제목</td>
					<td><input type="text" name="title"></td>
				</tr>
				<tr>
					<td bgcolor="pink">작성자</td>
					<td><input type="text" name="write" size="10"></td>
				</tr>
				<tr>
					<td bgcolor="pink">내용</td>
					<td><textarea name="content" cols="40" rows="10"> </textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="새 글등록"></td>
				</tr>

			</table>


		</form>

<br> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
		



	



</body>
</html>