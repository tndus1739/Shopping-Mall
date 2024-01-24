package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;
import product.ProductDTO;

public class ProductDAO {

	// SQL 쿼리를 접근하는 객체 선언
	
			Connection conn = null ;
			PreparedStatement pstmt = null ;
			ResultSet rs = null ;	
			
			// sql 쿼리를 적용하는 상수 선언
	
			// insert 쿼리
			
			private final String PRODUCT_INSERT =
				"insert into product ( id , name , price , content , regdate  ) "
				+ "values ( (select nvl(max(id) , 0 ) + 1 from product ) , ? , ? , ? , sysdate ) " ;  
		
			// PRODUCT테이블의 모든 레코드를 출력하는 쿼리
		
			private final String PRODECT_LIST = "select * from product order by id desc" ;
			
			// PRODUCT 테이블의 글 상세 조회 : 레코드 1개 -> 리턴으로 DTO 돌려줌
			private final String PRODECT_GET = "select * from product where id = ? ";
			
			// PRODUCT 테이블의 Update 쿼리
			private final String PRODECT_UPDATE = "update product set name = ? , price = ? , content = ? , regdate = ?  where id = ? ";   // ?에 변수값 넘어옴
			
			// PRODUCT 테이블의 Delete 쿼리
			private final String PRODECT_DELETE = "delete product where id = ? " ;
	
			
			// insertBoard ( ProductDTO dto ) 메소드  :
			public void insertProduct ( ProductDTO dto ) {				// insertBoard 메소드를 호출하면 try 구문이 실행됨
				System.out.println("insertBoard 처리 ");
			
				try { 
					
					 // conn , pstmt 객체 활성화
						conn = JDBCUtil.getConnection() ;
						//  M_BOARD_INSERT =  "insert into m_board ( id , name , price , content , regdate  ) "				
						// 					+ "values ( (select nvl(max(id) , 0 ) + 1 from m_board ) , ? , ? , ? , ? ) " ;  
						pstmt = conn.prepareStatement(PRODUCT_INSERT);
						
					 // pstmt 객체의 ? 에 변수값 할당

						pstmt.setString(1, dto.getName());          // 위의 insertBoard 에서 dto 로 들어온 값을 getTitle로 끄집어내서 ?에 값 할당
						pstmt.setInt(2, dto.getPrice());
						pstmt.setString(3, dto.getContent());
						
						
						
					// pstmt를 실행해서 DB에 저장
					   pstmt.executeUpdate();
					
					   System.out.println("Product 테이블에 값이 잘 insert 되었습니다.");
					   
					} catch (Exception e) { 
						
						System.out.println("Product 테이블에 값이  insert 실패했습니다.");
						e.printStackTrace();
						
					} finally {
						// 사용한 객체 제거
						JDBCUtil.close(pstmt, conn);
						
					}
					
				
				
				
			}
			
			// 제품항목 전체보기 getProductList 메소드
			public List<ProductDTO> getProductList (ProductDTO dto)  {     // -> 데이터 양이 많으면 리턴값을 List로
				
				
				// ★ ArrayList는 while 문 밖에서 선언 ( 안에서 선언하면 0번 방만 계속 반복) 
				// ★ ArrayList 내에 저장되는 ProductDTO 는 while 문 안에서 선언
				
				List <ProductDTO> ProductList = new ArrayList <>() ;
				
				try {
					
					
					conn = JDBCUtil.getConnection();
					pstmt = conn.prepareStatement(PRODECT_LIST);
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						
					ProductDTO board = new ProductDTO () ;
					
					board.setId(rs.getInt("ID"));
					board.setName(rs.getString("NAME"));
					board.setPrice(rs.getInt("PRICE"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegdate(rs.getDate("regdate"));
				
					
					ProductList.add(board);
					
					
					}
					
					
					
				} catch (Exception e) {
					
					System.out.println("DB select 실패");
					e.printStackTrace();
					
				} finally {
					
					JDBCUtil.close(rs, pstmt, conn);
					
				}
				
				
				return ProductList ;
			}
			
			
			// 제품 상세조회 메소드 getProduct.pp
			
			public ProductDTO getProduct (ProductDTO dto ) {
				
				System.out.println("getProduct.pp 호출 성공");
				
				
				
				ProductDTO board = new ProductDTO () ;
				
				try {
					
					conn = JDBCUtil.getConnection();
					// PRODECT_GET = "select * from m_board where id = ? "
					
					pstmt = conn.prepareStatement(PRODECT_GET);
					
					pstmt.setInt(1, dto.getId()); 
					
					rs = pstmt.executeQuery();
					
					while (rs.next()) {
						
						
						board.setId(rs.getInt("ID"));
						board.setName(rs.getString("NAME"));
						board.setPrice(rs.getInt("PRICE"));
						board.setContent(rs.getString("CONTENT"));
						board.setRegdate(rs.getDate("regdate"));
					
						System.out.println("RS의 레코드를 dto 저장 성공");
					}
					
					
				} catch (Exception e ) {
					
					System.out.println("DB select 실패");
					e.printStackTrace();
					
					
				} finally {
					
					JDBCUtil.close(rs, pstmt, conn);
				}
				
				return board ;
			}
			
			
			//  제품수정 메소드 : updateBoard(dto)
			
			public void updateBoard (ProductDTO dto) {
				
				System.out.println("updateBoard 메소드 호출됨");
				
				
				try {
					
				conn = JDBCUtil.getConnection()	;
				// PRODECT_UPDATE = "update m_board set name = ? , price = ? , content = ? , regdate = ?  where id = ? ";
				pstmt = conn.prepareStatement(PRODECT_UPDATE);
				
				// ? 변수에 값을 할당
				
				pstmt.setString(1, dto.getName());
				pstmt.setInt(2, dto.getPrice());
				pstmt.setString(3, dto.getContent());
				pstmt.setDate(4, dto.getRegdate());
				pstmt.setInt(5, dto.getId());
				
				// 쿼리를 실행
				
				pstmt.executeUpdate();
				
				System.out.println("DB 업데이트 성공");
				
					
				} catch (Exception e ) {
					
				System.out.println("DB 업데이트 실패");	
				e.printStackTrace();	
					
				} finally {
					
				JDBCUtil.close(pstmt, conn);	
				}
				
				
				
			}
			
			
			// 글 삭제 메소드 
			
			public void deleteProduct ( ProductDTO dto) {
				
				System.out.println("deleteProduct 메소드 호출됨");
				
				try {
				
				conn = 	JDBCUtil.getConnection();
				// PRODECT_DELETE = "delete m_board where id = ? "	
				pstmt = conn.prepareStatement(PRODECT_DELETE)	;
				
				// ? 변수에 값을 할당
				
				pstmt.setInt(1, dto.getId());		
				
				pstmt.executeUpdate();
				
				System.out.println("DB 삭제 성공");	
					
				} catch (Exception e) {
					
				System.out.println("DB 삭제 실패");
				e.printStackTrace();
				
				} finally {
					
				JDBCUtil.close(pstmt, conn);	
					
				}
				
				
				
			}
			
			
			
}
