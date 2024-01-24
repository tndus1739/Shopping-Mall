<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="m_board.M_BoardDTO" %>    


<%

		// session 변수에 담긴 값을 불러옴 : 서버의 RAM 
		
		M_BoardDTO board = new M_BoardDTO () ;

		//session변수의 값을 가져올 때는 다운캐스팅 필요
		board = ( M_BoardDTO ) session.getAttribute("board");    // getAttribute : 변수의 값을 가지고 옴)  

	


%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 글 상세 페이지</title>
</head>
<body>


	<h1> 글 상세 페이지 </h1>
	<hr> 
	<br> <br>
	
	<form method = "post" action = "updateBoard.bb">
	
		<!--  글 수정시 조건을 처리할 컬럼  -->
		<input type = "hidden" name = "id" value = "<%= board.getId()  %>" >
		
		<table border = "1" width = "700px" cellpedding = "5px">
			<tr> <td bgcolor = "pink" align ="center" > 제목 </td>
				 <td> <input type ="text" name = "title" value="<%= board.getM_title() %>" > </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 작성자 </td>
				 <td> <input type ="text" name = "write" value="<%= board.getM_write() %>" > </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 내용 </td>
				 <td> <textarea name ="content"  rows = "10" cols ="70" > <%= board.getM_cont() %>" > </textarea> </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 등록일 </td>
				 <td> <%= board.getRegdate() %> </td>
			</tr>
			
			<tr> <td bgcolor = "pink" align ="center"> 조회수 </td>
				 <td> <%= board.getCnt() %> </td>						<!-- 수정 못하도록 --> 
			</tr>
			
			<tr> <td colspan = "2" align = "center"> <input type = "submit" value = "수정"> </td>
			</tr>
	
	
	
		</table>
	
	
	</form>
	
	<br> <br>
	 <a href = "deleteBoard.bb?id=<%= board.getId() %>" > 
	  글삭제
	 </a>
	
	<p /> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
		
		<p /> <a href = "getBoardList.bb"> 글목록 </a>
		<p /> <a href = "insertBoard.jsp"> 새글쓰기 </a>
	
	

</body>
</html>