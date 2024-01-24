package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import m_board.M_BoardDAO;
import m_board.M_BoardDTO;
import member.MemberDAO;
import member.MemberDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//http://localhost:8181/JSP_MY_PROJ/*.mm
@WebServlet("*.mm")
public class Member_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Member_Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		// 한글이 깨지지 않도록 처리

		request.setCharacterEncoding("UTF-8");
		System.out.println("mm 요청을 처리하는 controller 입니다.");

		// URL : http://localhost:8181/JSP_MY_PROJ/*.mm (주소 전체)
		// URI : /JSP_MY_PROJ/*.mm (서버 주소 뒤에 나오는 부분)

		String uri = request.getRequestURI();
		System.out.println(uri);

		String path = uri.substring(uri.lastIndexOf("/")); // uri 의 마지막 "/" 뒤에 있는 것을 path 변수값으로 처리
		System.out.println(path);

		
		// 로그인
		
		if (path.equals("/login.mm")) {

			System.out.println("/login mm 요청 성공");

			// 1. client 에서 넘긴 파라미터 변수값을 받아서 변수에 저장

			String id = request.getParameter("id");
			String password = request.getParameter("password");

			// 2. 넘겨받은 값을 MemberDTO에 저장

			MemberDTO dto = new MemberDTO();

			dto.setId(id);
			dto.setPassword(password);

			// 3. UserDAO 의 login (dto)
			MemberDAO dao = new MemberDAO();

			// 리턴 받을 UsersDTO 선언

			MemberDTO user = new MemberDTO();

			user = dao.login(dto);

			// user가 null 인 경우 : 인증실패 , 그렇지 않을 경우 인증 성공

			if (user == null) {

				System.out.println("로그인 실패 ");
				response.sendRedirect("LoginForm.jsp");

			} else {

				// 세션의 변수의 값을 할당하고 view 페이지로 전송

				System.out.println("로그인 성공 ");
				HttpSession session = request.getSession();

				session.setAttribute("id", user.getId());
				session.setAttribute("role", user.getRole());

				response.sendRedirect("LoginForm.jsp");

			}

		} else if (path.equals("/logout.mm")) {

			System.out.println("logout.mm 요청 처리 ");

			// 1. 사용자 세션 변수의 모든 값을 삭제함
			HttpSession session = request.getSession();

			// invalidate(); : 세션 변수에 담긴 모든 변수의 값을 삭제
			session.invalidate();

			// 뷰페이지로 이동 (처음페이지로 이동)

			response.sendRedirect("http://localhost:8181/JSP_MY_PROJ");
			
			
		// 회원가입
			
		} else if (path.equals("/signIn.mm")) {

			System.out.println("/signIn.mm 요청 ");

			// 1. 변수 값 잘 들어오는지 확인

			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String addr = request.getParameter("addr");

			System.out.println("id : " + id);
			System.out.println("password : " + password);
			System.out.println("phone : " + phone);
			System.out.println("email : " + email);
			System.out.println("addr : " + addr);

			// 2. dto에 setter 주입

			MemberDTO dto = new MemberDTO();

			dto.setId(id);
			dto.setPassword(password);
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setAddr(addr);

			// 3. dao 에 signIn

			MemberDAO dao = new MemberDAO();

			dao.signIn(dto);

			// 4. insert

			System.out.println("DB 저장 성공");

			// 5. 뷰페이지 전송

			response.sendRedirect("getMemberList.mm");
			
			
		// 회원정보 리스트
			
		} else if (path.equals("/getMemberList.mm")) {

			System.out.println("/getMemberList.mm 요청 ");

			// 1. MemberDTO 의 객체 생성
			MemberDTO dto = new MemberDTO();
			// 2. MemberDAO
			MemberDAO dao = new MemberDAO();
			// 3.
			List<MemberDTO> MemberList = new ArrayList<>();

			MemberList = dao.getMemberList(dto);

			// client의 session 정보를 가져와서 session 변수에 할당

			HttpSession session = request.getSession();

			session.setAttribute("MemberList", MemberList);

			response.sendRedirect("getMemberList.jsp");
			
		// 회원 상세정보

		} else if (path.equals("/getMember.mm")) {
			// 1. client 에서 넘어오는 파라미터 id 변수의 값을 읽어서 dto에 저장 후 dao.getMember(dto)

			String id = request.getParameter("id");

			// 2. dto에 id 값을 setter 주입
			MemberDTO dto = new MemberDTO();
			dto.setId(id);

			// 3. DAO의 getMember (dto) 호출 후 리턴 값을 받아서 저장
			
			MemberDAO dao = new MemberDAO();

			// 리턴값을 받을 DTO 선언

			MemberDTO member = new MemberDTO();

			member = dao.getMember(dto);

			// 4. 세션 변수에 저장후 뷰 페이지로 전송
			
			HttpSession session = request.getSession();

			session.setAttribute("member", member);

			// 5. 뷰 페이지
			response.sendRedirect("getMember.jsp");
			
	     // 회원정보 수정
			
		} else if (path.equals("/updateMember.mm")) {

			System.out.println("/updateMember.mm 요청");

			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String addr = request.getParameter("addr");

			System.out.println(id);
			System.out.println(password);
			System.out.println(phone);
			System.out.println(email);
			System.out.println(addr);

			// 변수를 MemberDTO에 setter 주입
			
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPassword(password);
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setAddr(addr);

			MemberDAO dao = new MemberDAO();

			dao.updateMember(dto);

			response.sendRedirect("getMemberList.mm");
			
		// 회원정보 삭제
		
		} else if (path.equals("/deleteMember.mm")) {

			System.out.println("/deleteMember.mm 요청");

			// 1. 클라이언트의 파라미터 변수의 값 할당 : id

			String id = request.getParameter("id");

			// 2. 변수의 값을 BoardDTO에 주입
			MemberDTO dto = new MemberDTO();

			dto.setId(id);

			// 3. BoardDAO의 메소드 호출 : deleteBoard(dto)
			MemberDAO dao = new MemberDAO();
			
			dao.deleteMember(dto);

			// 4. 뷰페이지 이동
			response.sendRedirect("getMemberList.mm");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
