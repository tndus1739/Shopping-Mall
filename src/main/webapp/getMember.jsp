<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.*"%>
<%@ page import="member.MemberDTO"%>
<!--  board : 패키지 이름 / BoardDTO : 파일 이름 -->


<!-- jsp파일에서 말고 위의 자바 백엔드 파일에서 수정을 하면 톰캣이 자동으로 재실행 -->

<%
		// session 변수에 담긴 값을 불러옴 : 서버의 RAM 
		
		MemberDTO member = new MemberDTO () ;

		//session변수의 값을 가져올 때는 다운캐스팅 필요
	member = ( MemberDTO ) session.getAttribute("member");    // getAttribute : 변수의 값을 가지고 옴)  

	
  %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 회원 상세정보 </h1>
	<hr> 
	<br> <br>
	
	<form method = "post" action = "updateMember.mm">
	
		<!--  글 수정시 조건을 처리할 컬럼  -->
		<input type = "hidden" name = "id" value = "<%= member.getId()  %>" >
		
		<table border = "1" width = "700px" cellpedding = "5px">
			<tr> <td bgcolor = "pink" align ="center" > 전화번호 </td>
				 <td> <input type ="text" name = "name" value="<%= member.getPhone() %>" > </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 이메일 </td>
				 <td> <input type ="text" name = "price" value="<%= member.getEmail() %>" > </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 주소 </td>
				 <td> <textarea name ="content"  rows = "10" cols ="70" > <%= member.getAddr() %>" > </textarea> </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 비밀번호 </td>
				 <td> <%= member.getPassword() %> </td>
			</tr>
			

			
			<tr> <td colspan = "2" align = "center"> <input type = "submit" value = "수정"> </td>
			</tr>
	
	
	
		</table>
	
	
	</form>
	
	
	
	<br> <br>
	 <a href = "deleteMember.mm?id=<%=member.getId() %>" > 
	  회원정보삭제
	 </a>
	
	<p /> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
		
		<p /> <a href = "getProductList.do"> 회원목록 </a>
	
	
</body>
</html>