package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import m_board.M_BoardDAO;
import m_board.M_BoardDTO;
import product.ProductDAO;
import product.ProductDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//http://localhost:8181/JSP_MY_PROJ/*.pp
@WebServlet ("*.pp")
public class Product_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Product_Controller() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		// 한글이 깨지지 않도록 처리
		  
			request.setCharacterEncoding("UTF-8");
			System.out.println("pp 요청을 처리하는 controller 입니다.");
				    
			// URL : http://localhost:8181/JSP_MY_PROJ/*.mm	 (주소 전체)
			// URI : /JSP_MY_PROJ/*.mm  (서버 주소 뒤에 나오는 부분)
					
			String uri = request.getRequestURI();
			System.out.println(uri);
					
			String path = uri.substring(uri.lastIndexOf("/"));   // uri 의 마지막 "/" 뒤에 있는 것을 path 변수값으로 처리
			System.out.println(path);
	
	

			if (path.equals("/insertProduct.pp")) {
			
				System.out.println("/insertProduct.pp 요청 ");
				
				// 1. 변수 값 잘 들어오는지 확인
				
				
				String name = request.getParameter("name");    
				int price = Integer.parseInt(request.getParameter("price"));
				String content = request.getParameter("content");
				
				System.out.println("name : " + name );  
				System.out.println("price : " + price );
				System.out.println("content : " + content );
				
				// 2. dto에 setter 주입
				
				ProductDTO dto = new ProductDTO () ;
				
				dto.setName(name);
				dto.setPrice(price);
				dto.setContent(content);
				
				// 3. dao 에 insertBoard
				
				ProductDAO dao = new ProductDAO () ;
				
				dao.insertProduct(dto);
				
				// 4. insert 
				
				System.out.println("DB 저장 성공");
				
				// 5. 뷰페이지 전송
				
				response.sendRedirect("getProductList.pp");
			
			}else if (path.equals("/getProductList.pp")) {
				
				System.out.println("/getProductList.pp 요청 ");
				
				// 1. M_BoardDTO 의 객체 생성
				ProductDTO dto = new ProductDTO () ;
				// 2. M_BoardDAO 
				ProductDAO dao = new ProductDAO () ;
				// 3. 
				List < ProductDTO>  productList = new ArrayList <>();
				
				productList = dao.getProductList (dto); 
				
				//  Product의 session 정보를 가져와서 session 변수에 할당
				
				HttpSession session = request.getSession();
				
				session.setAttribute("productList", productList);
				
				response.sendRedirect("getProductList.jsp");
				
			}else if (path.equals("/getProduct.pp")) {
				
				//  1. product 에서 넘어오는 파라미터 id 변수의 값을 읽어서 dto에 저장 후 dao.getProduct(dto)
				
				int id = Integer.parseInt(request.getParameter("id")) ;
				// 2. dto에 seq 값을  setter 주입
				ProductDTO dto = new ProductDTO () ;
				dto.setId(id);
				
				// 3. DAO의 getBoard (dto) 호출 후 리턴 값을 받아서 저장
				ProductDAO dao = new ProductDAO () ;
				
				// 리턴값을 받을 DTO 선언
				
				ProductDTO product = new ProductDTO () ;
				
				product = dao.getProduct(dto);
				
				// 4. 세션 변수에 저장후 뷰 페이지로 전송
				HttpSession session = request.getSession();
				
				session.setAttribute("product", product) ;
				
				// 5. 뷰 페이지
				response.sendRedirect("getProduct.jsp");
				
				
				
			}else if (path.equals("/updateProduct.pp")) {
				
				System.out.println("/updateProduct.pp 요청");
				
				int id = Integer.parseInt(request.getParameter("id")) ;
				
				String name = request.getParameter("name");    
				int price = Integer.parseInt(request.getParameter("price"));
				String content = request.getParameter("content");
				
				System.out.println(name);
				System.out.println(price);
				System.out.println(content);
				System.out.println(id);
				
				
				//  변수를 BoardDTO에 setter 넣기
				
				ProductDTO dto = new ProductDTO ();
				
				dto.setName(name);
				dto.setPrice(price);
				dto.setContent(content);
				dto.setId(id);
				
				ProductDAO dao = new ProductDAO();
				
				dao.updateBoard(dto);
				
				response.sendRedirect("getProductList.pp");
				
				
			}else if (path.equals("/deleteProduct.pp")) {
				
				System.out.println("/deleteProduct.pp 요청");
				 
				// 1. 클라이언트의 파라미터 변수의 값 할당 : id
				
				String i_id = request.getParameter("id");
				System.out.println(i_id);// 파라미터로 넘어오는 seq는 String이라서 int 로 변환해줘야함
				int id = Integer.parseInt(i_id);
				
				// 2. 변수의 값을 BoardDTO에 주입
				ProductDTO dto = new ProductDTO () ;
				
				dto.setId(id);
				
				// 3. BoardDAO의 메소드 호출 : deleteBoard(dto)
				ProductDAO dao = new ProductDAO () ;
				dao.deleteProduct(dto);	
				
				
				// 4. 뷰페이지 이동 
				response.sendRedirect("getProductList.pp");
				
				
				
				
			}
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
