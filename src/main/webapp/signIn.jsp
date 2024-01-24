<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align:center">


	<h1>[회원가입]</h1>

<!--form 태그안에 변수 값을 담아서 insertProduct.pp 호출   -->

	<!-- mm 요청은 Member_Controller.java에서 받도록 설정   -->
<form method="post" action="signIn.mm">
	<table border="1" style="margin-left: auto; margin-right: auto;">

		<tr>
			<td bgcolor="pink">아이디</td>
			<td><input type="text" name="id" size="30"></td>
		</tr>
		<tr>
			<td bgcolor="pink">비밀번호</td>
			<td><input type="text" name="password" size="30"></td>
		</tr>

		<tr>
			<td bgcolor="pink">전화번호</td>
			<td><input type="text" name="phone" size="30"></td>
		</tr>
		<tr>
			<td bgcolor="pink">이메일</td>
			<td><input type="text" name="email" size="30"></td>
		</tr>
		<tr>
			<td bgcolor="pink">주소</td>
			<td><input type="text" name="addr" size="30"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="회원가입"></td>
			
			
		</tr>

	</table>


</form>

<br>

<div style="text-align: center;">
    <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
</div>

</body>
</html>