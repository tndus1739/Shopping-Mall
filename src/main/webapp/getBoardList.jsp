<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--  java.util.* : List, ArrayList -->

<%@ page import="java.util.*"%>
<%@ page import="m_board.M_BoardDTO"%>
<!--  board : 패키지 이름 / BoardDTO : 파일 이름 -->


<!-- jsp파일에서 말고 위의 자바 백엔드 파일에서 수정을 하면 톰캣이 자동으로 재실행 -->

<%
	// Session 변수에 저장된 ArrayList 를 가지고 옴

	List<M_BoardDTO> boardList = new ArrayList<>();
	//     타입          변수
	//  세션에서 가져온 값은 Object 타입이어서 (List 타입으로 변환)

try { // catch 블락은 마지막 밑에

	boardList = (List) session.getAttribute("boardList"); // list 타입으로 캐스팅해줘야 함  // 바로 호출하지말고 do요청을 해야함
	// boardList가  null 값이기 때문에 그냥 불러오면 오류가 남

	// boardList = (List<BoardDTO>) session.getAttribute("boardList"); -> <BoardDTO> 생략가능
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<body>
	<center>
		<h1>글 목록</h1>
		<hr>

		<table border="1" width="700px">

			<tr>
				<th bgcolor="pink" width="100px">번호</th>
				<th bgcolor="pink" width="200px">제목</th>
				<th bgcolor="pink" width="150px">작성자</th>
				<th bgcolor="pink" width="150px">내용</th>
				<th bgcolor="pink" width="150px">등록일</th>
				<th bgcolor="pink" width="100px">조회수</th>
			</tr>


			<!--  ArrayList의 BoardDTO를 끄집어내서 출력 : loop 돌리면서 출력 -->

			<%
			for (M_BoardDTO m : boardList) {
			%>


			<tr>
				<td align="center"><%=m.getId()%></td>

				<!--  제목에 링크를 건다 : 글 상세 내용을 볼 수 있도록 ( get title에 링크 걸기) -->

				<td><a href="getBoard.bb?id=<%=m.getId()%>"> <%=m.getM_title()%>
				</a> <!--  링크 대소문자 주의  --></td>
				<td><%=m.getM_write()%></td>
				<td><%=m.getM_cont()%></td>
				<td><%=m.getRegdate()%></td>
				<td><%=m.getCnt()%></td>
			</tr>

			<%
			} // for문 블락의 끝

			// 모두 사용됨 : boardList
			// 세션 변수의 값을 제거 : 서버의 메모리에서 세션 변수 boardList에 저장한 값을 제거

			session.removeAttribute("boardList");

			} catch (Exception e) {

			response.sendRedirect("getBoardList.bb");
			}
			%>





		</table>

		<br> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
		<p /> <a href = "insertBoard.jsp"> 게시물 작성 </a>

	</center>
</body>
</html>