<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	// 세션 변수의 값을 읽어옴
	String sessionId = (String) session.getAttribute("id");
	%>


	<%=sessionId%> 님 로그인 되었습니다.


<h1> [회원 페이지]</h1>

<hr>
	<br>
	<a href="insertBoard.jsp"> 게시판 글쓰기 </a>
	<p />
	<a href="getBoardList.bb"> 게시판 리스트 페이지 </a>
	<p />
	<a href = "logout.mm"> 로그아웃 </a>
	<p />
	
	
</body>
</html>