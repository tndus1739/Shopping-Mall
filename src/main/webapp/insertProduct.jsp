<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
try {
	String sessionRole = (String) session.getAttribute("role");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<!-- view 페이지 : 관리자에게 보여지는 페이지 -->


	<h1>제품 등록 페이지</h1>
	<hr>

	<%
	if (sessionRole.equals("admin")) {
	
	%>





	<!--form 태그안에 변수 값을 담아서 insertProduct.pp 호출   -->
	<form method="post" action="insertProduct.pp">
		<!-- do 요청은 Board_Controller.java에서 받도록 설정   -->
		<table border="1">
		
			<tr>
				<td bgcolor="pink">제품명</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td bgcolor="pink">가격</td>
				<td><input type="text" name="price" size="20"></td>
			</tr>
			<tr>
				<td bgcolor="pink">제품설명</td>
				<td><textarea name="content" cols="30" rows="10"> </textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="제품등록"></td>
			</tr>

		</table>


	</form>

	<%
	} else {
	%>

	당신은 admin이 아닙니다. admin 계정으로 로그인해야 합니다.

	<%
	}

	} catch (Exception e) {
	response.sendRedirect("LoginForm.jsp");
	}
	%>




	<br>
	<a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>











</body>
</html>