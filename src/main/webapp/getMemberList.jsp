<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--  java.util.* : List, ArrayList -->

<%@ page import="java.util.*"%>
<%@ page import="member.MemberDTO"%>
<!--  board : 패키지 이름 / BoardDTO : 파일 이름 -->




<!-- jsp파일에서 말고 위의 자바 백엔드 파일에서 수정을 하면 톰캣이 자동으로 재실행 -->

<%
	// Session 변수에 저장된 ArrayList 를 가지고 옴

	List<MemberDTO> memberList = new ArrayList<>();
	//     타입          변수
	//  세션에서 가져온 값은 Object 타입이어서 (List 타입으로 변환)

try { // catch 블락은 마지막 밑에

	memberList = (List) session.getAttribute("MemberList"); // list 타입으로 캐스팅해줘야 함  // 바로 호출하지말고 do요청을 해야함
	// memberList가  null 값이기 때문에 그냥 불러오면 오류가 남

	// memberList = (List<MemberDTO>) session.getAttribute("memberList"); -> <MemberDTO> 생략가능
%>

    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


		<h1>[회원정보 리스트]</h1>
		<hr>

		<table border="1" width="700px">

			<tr>
				<th bgcolor="pink" width="100px">아이디</th>
				<th bgcolor="pink" width="200px">전화번호</th>
				<th bgcolor="pink" width="150px">이메일</th>
				<th bgcolor="pink" width="150px">등록일</th>
				<th bgcolor="pink" width="100px">주소</th>
				<th bgcolor="pink" width="100px">권한</th>
			</tr>


			<!--  ArrayList의 MemberDTO를 끄집어내서 출력 : loop 돌리면서 출력 -->

			<%
			for (MemberDTO m : memberList) {
			%>


			<tr>
				<td align="center"><a href="getMember.mm?id=<%=m.getId()%>"><%=m.getId()%></a></td>

				<!--  아이디에 링크를 건다 : 회원 상세정보를 볼 수 있도록 ( getId에 링크 걸기) -->
				
				
				<td> <%=m.getPhone()%> </td>
			 <!--  링크 대소문자 주의  --></td>
				<td><%=m.getEmail()%></td>
				<td><%=m.getRegdate()%></td>
				<td><%=m.getAddr()%></td>
				<td><%=m.getRole()%></td>
			</tr>



			<%
			} // for문 블락의 끝

			// 모두 사용됨 : memberList
			// 세션 변수의 값을 제거 : 서버의 메모리에서 세션 변수 memberList에 저장한 값을 제거

			session.removeAttribute("MemberList");

			} catch (Exception e) {

			response.sendRedirect("getMemberList.mm");
			}
			%>


		</table>

		<br> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>

		<br> <a href="http://localhost:8181/JSP_MY_PROJ/admin.jsp"> 관리자페이지로 </a>


 

</body>
</html>