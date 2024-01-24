package m_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.Jdbc41Bridge;

import m_board.M_BoardDTO;
import m_board.M_BoardDTO;
import m_board.M_BoardDTO;
import common.JDBCUtil;

public class M_BoardDAO {

	Connection conn = null ;
	PreparedStatement pstmt = null ;
	ResultSet rs = null ;
	
		// insert 쿼리
	
		private final String M_BOARD_INSERT =
			"insert into m_board ( id , m_title , m_write , m_cont  ) "
			+ "values ( (select nvl(max(id) , 0 ) + 1 from m_board ) , ? , ? , ?  ) " ;  
	
		// DB의 M_Board 테이블의 모든 레코드를 출력하는 쿼리
	
		private final String M_BOARD_LIST = "select * from m_board order by id desc" ;
		
		// DB의 M_Board 테이블의 글 상세 조회 : 레코드 1개 -> 리턴으로 DTO 돌려줌
		private final String M_BOARD_GET = "select * from m_board where id = ? ";
		
		// DB의 M_Board 테이블의 Update 쿼리
		private final String M_BOARD_UPDATE = "update m_board set m_title = ? , m_write = ? , m_cont = ? , cnt = ?  where id = ? ";   // ?에 변수값 넘어옴
		
		// DB의 M_Board 테이블의 Delete 쿼리
		private final String M_BOARD_DELETE = "delete m_board where id = ? " ;
		
		// 글 조회수 증가시키는 쿼리
		
		private final String ADD_CNT = "update m_board set cnt = cnt + 1 where id = ? " ;
	
		
		// insertBoard ( M_BoardDTO dto ) 메소드  :
		public void insertBoard ( M_BoardDTO dto ) {				// insertBoard 메소드를 호출하면 try 구문이 실행됨
			System.out.println("insertBoard 기능 처리 ");
			
			try { 
				
			 // conn , pstmt 객체 활성화
				conn = JDBCUtil.getConnection() ;
				// M_BOARD_INSERT = "insert into m_board ( id , m_title , m_write , m_cont , regdate  ) "
				//                  + "values ( (select nvl(max(id) , 0 ) + 1 from m_board ) , ? , ? , ? , ? ) "
				pstmt = conn.prepareStatement(M_BOARD_INSERT);
				
			 // pstmt 객체의 ? 에 변수값 할당

				pstmt.setString(1, dto.getM_title());          // 위의 insertBoard 에서 dto 로 들어온 값을 getTitle로 끄집어내서 ?에 값 할당
				pstmt.setString(2, dto.getM_write());
				pstmt.setString(3, dto.getM_cont());
				
				
			// pstmt를 실행해서 DB에 저장
			   pstmt.executeUpdate();
			
			   System.out.println("M_Board 테이블에 값이 잘 insert 되었습니다.");
			   
			} catch (Exception e) { 
				
				System.out.println("M_Board 테이블에 값이  insert 실패했습니다.");
				e.printStackTrace();
				
			} finally {
				// 사용한 객체 제거
				JDBCUtil.close(pstmt, conn);
				
			}
			
		
		}
		
		// 리스트 전체보기 getBoardList 메소드
		public List<M_BoardDTO> getBoardList (M_BoardDTO dto)  {     // -> 데이터 양이 많으면 리턴값을 List로
			
			
			// ★ ArrayList는 while 문 밖에서 선언 ( 안에서 선언하면 0번 방만 계속 반복) 
			// ★ ArrayList 내에 저장되는 BoardDTO 는 while 문 안에서 선언
			
			List<M_BoardDTO> boardList = new ArrayList <>() ;
			
			try {
				
				
				conn = JDBCUtil.getConnection();
				pstmt = conn.prepareStatement(M_BOARD_LIST);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
				M_BoardDTO board = new M_BoardDTO () ;
				
				board.setId(rs.getInt("ID"));
				board.setM_title(rs.getString("M_TITLE"));
				board.setM_write(rs.getString("M_WRITE"));
				board.setM_cont(rs.getString("M_CONT"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("CNT"));	
				
				boardList.add(board);
				
				
				}
				
				
				
			} catch (Exception e) {
				
				System.out.println("DB select 실패");
				e.printStackTrace();
				
			} finally {
				
				JDBCUtil.close(rs, pstmt, conn);
				
			}
			
			
			return boardList ;
			
		
}	
		
		// 글 상세조회 메소드 getBoard.bb
		
		public M_BoardDTO getBoard (M_BoardDTO dto ) {
			
			System.out.println("getBoard.bb 호출 성공");
			
			// 조회수 증가
			add_cnt(dto) ;
			
			M_BoardDTO board = new M_BoardDTO () ;
			
			try {
				
				conn = JDBCUtil.getConnection();
				// M_BOARD_GET = "select * from m_board where id = ? "
				
				pstmt = conn.prepareStatement(M_BOARD_GET);
				
				pstmt.setInt(1, dto.getId()); 
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					
					board.setId(rs.getInt("ID"));
					board.setM_title(rs.getString("M_TITLE"));
					board.setM_write(rs.getString("M_WRITE"));
					board.setM_cont(rs.getString("M_CONT"));
					board.setRegdate(rs.getDate("regdate"));
					board.setCnt(rs.getInt("CNT"));	
					
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
		
		
		//  글 수정 메소드 : updateBoard(dto)
		
		public void updateBoard (M_BoardDTO dto) {
			
			System.out.println("updateBoard 메소드 호출됨");
			
			
			try {
				
			conn = JDBCUtil.getConnection()	;
			// M_BOARD_UPDATE = "update m_board set m_title = ? , m_write = ? , m_cont = ? , regdate = ? , cnt = ?  where id = ? ";
			pstmt = conn.prepareStatement(M_BOARD_UPDATE);
			
			// ? 변수에 값을 할당
			
			pstmt.setString(1, dto.getM_title());
			pstmt.setString(2, dto.getM_write());
			pstmt.setString(3, dto.getM_cont());
			pstmt.setInt(4, dto.getCnt());
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
		
		public void deleteBoard ( M_BoardDTO dto) {
			
			System.out.println("deleteBoard 메소드 호출됨");
			
			try {
			
			conn = 	JDBCUtil.getConnection();
			// BOARD_DELETE = "delete board where seq = ? " 	
			pstmt = conn.prepareStatement(M_BOARD_DELETE)	;
			
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
		
		
		
		// 조회수를 증가시키는 메소드
		
		public void add_cnt ( M_BoardDTO dto) {
			
		
		try {
			
			conn = JDBCUtil.getConnection();
			// ADD_CNT = "update m_board set cnt = cnt + 1 where id = ? "	
			pstmt = conn.prepareStatement(ADD_CNT);
			
			pstmt.setInt(1, dto.getId());
			
			pstmt.executeUpdate();
			System.out.println("조회수 증가 성공");
		} catch ( Exception e) {
			
			e.printStackTrace();
			System.out.println("조회수 증가 실패");
		} finally {
			
			JDBCUtil.close(pstmt, conn);	
		}
			
			
			
			
		}
		
	
		
		
}
