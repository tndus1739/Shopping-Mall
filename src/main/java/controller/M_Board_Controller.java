package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import m_board.M_BoardDAO;
import m_board.M_BoardDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import m_board.M_BoardDTO;
import m_board.M_BoardDAO;
//http://localhost:8181/JSP_MY_PROJ/*.bb 
@WebServlet ("*.bb")
public class M_Board_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public M_Board_Controller() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	
		// 한글이 깨지지 않도록 처리
	  
		request.setCharacterEncoding("UTF-8");
		System.out.println("bb 요청을 처리하는 controller 입니다.");
	    
		// URL : http://localhost:8181/JSP_MY_PROJ/*.bb	 (주소 전체)
		// URI : /JSP_MY_PROJ/*.bb  (서버 주소 뒤에 나오는 부분)
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String path = uri.substring(uri.lastIndexOf("/"));   // uri 의 마지막 "/" 뒤에 있는 것을 path 변수값으로 처리
		System.out.println(path);
		
		
		if (path.equals("/insertBoard.bb")) {
		
			System.out.println("/insertBoard.bb 요청 ");
			
			// 1. 변수 값 잘 들어오는지 확인
			
			
			String m_title = request.getParameter("title");    
			String m_write = request.getParameter("write");
			String m_cont = request.getParameter("content");
			
			System.out.println("title : " + m_title );  
			System.out.println("write : " + m_write );
			System.out.println("content : " + m_cont );
			
			// 2. dto에 setter 주입
			
			M_BoardDTO dto = new M_BoardDTO () ;
			
			dto.setM_title(m_title);
			dto.setM_write(m_write);
			dto.setM_cont(m_cont);
			
			// 3. dao 에 insertBoard
			
			M_BoardDAO dao = new M_BoardDAO () ;
			
			dao.insertBoard(dto);
			
			// 4. insert 
			
			System.out.println("DB 저장 성공");
			
			// 5. 뷰페이지 전송
			
			response.sendRedirect("getBoardList.bb");
		
		}else if (path.equals("/getBoardList.bb")) {
			
			System.out.println("/getBoardList.bb 요청 ");
			
			// 1. M_BoardDTO 의 객체 생성
			M_BoardDTO dto = new M_BoardDTO () ;
			// 2. M_BoardDAO 
			M_BoardDAO dao = new M_BoardDAO () ;
			// 3. 
			List<M_BoardDTO> boardList = new ArrayList <>();
			
			boardList = dao.getBoardList(dto);
			
			//  client의 session 정보를 가져와서 session 변수에 할당
			
			HttpSession session = request.getSession();
			
			session.setAttribute("boardList", boardList);
			
			response.sendRedirect("getBoardList.jsp");
			
		}else if (path.equals("/getBoard.bb")) {
			
			//  1. client 에서 넘어오는 파라미터 id 변수의 값을 읽어서 dto에 저장 후 dao.getBoard(dto)
			
			int id = Integer.parseInt(request.getParameter("id")) ;
			
			// 2. dto에 seq 값을  setter 주입
			M_BoardDTO dto = new M_BoardDTO () ;
			dto.setId(id);
			
			// 3. DAO의 getBoard (dto) 호출 후 리턴 값을 받아서 저장
			M_BoardDAO dao = new M_BoardDAO () ;
			
			// 리턴값을 받을 DTO 선언
			
			M_BoardDTO board = new M_BoardDTO () ;
			
			board = dao.getBoard(dto);
			
			// 4. 세션 변수에 저장후 뷰 페이지로 전송
			HttpSession session = request.getSession();
			
			session.setAttribute("board", board) ;
			
			// 5. 뷰 페이지
			response.sendRedirect("getBoard.jsp");
			
			
			
		}else if (path.equals("/updateBoard.bb")) {
			
			System.out.println("/updateBoard.bb 요청");
			
			int id = Integer.parseInt(request.getParameter("id")) ;
			
			String m_title = request.getParameter("title");
			String m_write = request.getParameter("write");
			String m_cont = request.getParameter("content");
			
			System.out.println(m_title);
			System.out.println(m_write);
			System.out.println(m_cont);
			System.out.println(id);
			
			
			//  변수를 BoardDTO에 setter 주입
			M_BoardDTO dto = new M_BoardDTO();
			dto.setM_title(m_title);
			dto.setM_write(m_write);
			dto.setM_cont(m_cont);
			dto.setId(id);
			
			M_BoardDAO dao = new M_BoardDAO();
			
			dao.updateBoard(dto);
			
			response.sendRedirect("getBoardList.bb");
			
			
		}else if (path.equals("/deleteBoard.bb")) {
			
			System.out.println("/deleteBoard.bb 요청");
			 
			// 1. 클라이언트의 파라미터 변수의 값 할당 : id
			
			String i_id = request.getParameter("id");
			System.out.println(i_id);// 파라미터로 넘어오는 seq는 String이라서 int 로 변환해줘야함
			int id = Integer.parseInt(i_id);
			
			// 2. 변수의 값을 BoardDTO에 주입
			M_BoardDTO dto = new M_BoardDTO () ;
			
			dto.setId(id);
			
			// 3. BoardDAO의 메소드 호출 : deleteBoard(dto)
			M_BoardDAO dao = new M_BoardDAO () ;
			dao.deleteBoard(dto);	
			
			
			// 4. 뷰페이지 이동 
			response.sendRedirect("getBoardList.bb");
			
			
			
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
