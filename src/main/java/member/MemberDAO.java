package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.Jdbc41Bridge;

import common.JDBCUtil;
import m_board.M_BoardDTO;
import member.MemberDTO;

public class MemberDAO {

		// SQL 쿼리를 접근하는 객체 선언
	
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;	
		
		// sql 쿼리를 적용하는 상수 선언
		
		// 로그인 쿼리
		private final String MEMBER_LOGIN = "select * from member where id = ? and password = ? " ;
		
		// 회원등록 쿼리
		private final String MEMBER_SIGNIN = "insert into member values ( ? , ? , ? , ? , sysdate , ? , default )";  
		
		// DB의 Member 테이블의 모든 레코드를 출력하는 쿼리
		
		private final String MEMBER_LIST = "select * from member order by id asc" ;
			
		// DB의 Member 테이블의 글 상세 조회 : 레코드 1개 -> 리턴으로 DTO 돌려줌
		private final String MEMBER_GET = "select * from member where id = ? ";
			
		// DB의 Member 테이블의 Update 쿼리
		private final String MEMBER_UPDATE = "update member set password = ? , phone = ? , email = ?  , addr = ? , role = ? where id = ? ";   // ?에 변수값 넘어옴
			
		// DB의 Member 테이블의 Delete 쿼리
		private final String MEMBER_DELETE = "delete member where id = ? " ;
			
		// 메소드
		
		
		public MemberDTO login (MemberDTO dto) {
			
			System.out.println("login메소드 호출");
			
			MemberDTO mem = null; 
			
			try {
				
				conn = JDBCUtil.getConnection();
				pstmt = conn.prepareStatement(MEMBER_LOGIN);
				
				// ? 변수의 값 할당
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPassword());
				
				rs = pstmt.executeQuery() ;
				
				
				while (rs.next())  {
					
					// 리턴으로 돌려줄 dto 선언
					
					 mem = new  MemberDTO () ;
					
					mem.setId(rs.getString("ID"));
					mem.setPassword(rs.getNString("PASSWORD"));
					mem.setPhone(rs.getNString("PHONE"));
					mem.setEmail(rs.getNString("EMAIL"));
					mem.setRegdate(rs.getDate("REGDATE"));
					mem.setAddr(rs.getString("ADDR"));
					mem.setRole(rs.getString("ROLE"));
					
					System.out.println("인증성공 : 해당 ID와 PASSWORD가 정확히 일치합니다.");
				}
				
				
				
				
			} catch ( Exception e) {
			
			System.out.println("인증시 문제 발생");	
			e.printStackTrace();
				
			} finally {
				
			JDBCUtil.close(rs, pstmt, conn);
			
			}
			
			
			
			return mem;
			
		}
		
		
		
		// insertMember ( MemberDTO dto ) 메소드 
		
		public void signIn (MemberDTO dto) {
		
		System.out.println("signIn 메소드 호출");
		
		try {
			
			
			conn = JDBCUtil.getConnection();
			// USER_SIGNIN = "insert into member values ( ? , ? , ? , ? , ? )";  
			
			
			pstmt = conn.prepareStatement(MEMBER_SIGNIN);
			
		    // pstmt 객체의 ? 에 변수값 할당
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getAddr());
			
			
			// pstmt를 실행해서 DB에 저장
			   pstmt.executeUpdate();
			
			   System.out.println("Member 테이블에 값이 잘 insert 되었습니다.");
			
		} catch (Exception e) {
		
		System.out.println("Member 테이블에  insert를 실패했습니다.");	
		e.printStackTrace();
		
		} finally {
		
		// 사용한 객체 제거
			
		JDBCUtil.close(pstmt, conn);	
			
		}
		
	}
	
		// 회원리스트 전체보기 getMemberList 메소드
		
				public List<MemberDTO> getMemberList (MemberDTO dto)  {     // -> 데이터 양이 많으면 리턴값을 List로
					
					
					// ★ ArrayList는 while 문 밖에서 선언 ( 안에서 선언하면 0번 방만 계속 반복) 
					// ★ ArrayList 내에 저장되는 MemberDTO 는 while 문 안에서 선언
					
					List<MemberDTO> memberList = new ArrayList <>() ;
					
					try {
						
						
						conn = JDBCUtil.getConnection();
						pstmt = conn.prepareStatement(MEMBER_LIST);
						rs = pstmt.executeQuery();
						
						while (rs.next()) {
							
							MemberDTO member = new MemberDTO () ;
						
						member.setId(rs.getString("ID"));
						member.setPassword(rs.getString("PASSWORD"));
						member.setPhone(rs.getString("PHONE"));
						member.setEmail(rs.getString("EMAIL"));
						member.setRegdate(rs.getDate("regdate"));
						member.setAddr(rs.getString("ADDR"));	
						member.setRole(rs.getString("ROLE"));	
						
						memberList.add(member);
						
						
						}
						
						
						
					} catch (Exception e) {
						
						System.out.println("DB select 실패");
						e.printStackTrace();
						
					} finally {
						
						JDBCUtil.close(rs, pstmt, conn);
						
					}
					
					
					return memberList ;
					
				
		}	
				
				//  회원정보 상세조회 메소드 getMember.mm
				
				public MemberDTO getMember (MemberDTO dto ) {
					
					System.out.println("getMember.mm 호출 성공");
					
		
					MemberDTO member = new MemberDTO () ;
					
					try {
						
						conn = JDBCUtil.getConnection();
						// MEMBER_GET = "select * from member where id = ? ";
						
						pstmt = conn.prepareStatement(MEMBER_GET);
						
						pstmt.setString(1, dto.getId()); 
						
						rs = pstmt.executeQuery();
						
						while (rs.next()) {
							
							
							member.setId(rs.getString("ID"));
							member.setPassword(rs.getString("PASSWORD"));
							member.setPhone(rs.getString("PHONE"));
							member.setEmail(rs.getString("EMAIL"));
							member.setRegdate(rs.getDate("REGDATE"));
							member.setAddr(rs.getString("ADDR"));	
							member.setRole(rs.getString("ROLE"));	
							
							System.out.println("RS의 레코드를 dto 저장 성공");
						}
						
						
					} catch (Exception e ) {
						
						System.out.println("DB select 실패");
						e.printStackTrace();
						
						
					} finally {
						
						JDBCUtil.close(rs, pstmt, conn);
					}
					
					return member;
				}
				
				
				//  회원정보 수정 메소드 : updateMember(dto)
				
				public void updateMember (MemberDTO dto) {
					
					System.out.println("updateMember 메소드 호출됨");
					
					
					try {
						
					conn = JDBCUtil.getConnection()	;
					//MEMBER_UPDATE = "update member set password = ? , phone = ? , email = ?  , addr = ? , role = ? where id = ? ";
					pstmt = conn.prepareStatement(MEMBER_UPDATE);
					
					// ? 변수에 값을 할당
					
					pstmt.setString(1, dto.getPassword());
					pstmt.setString(2, dto.getPhone());
					pstmt.setString(3, dto.getEmail());
					pstmt.setString(4, dto.getAddr());
					pstmt.setString(5, dto.getId());
					
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
				
				
				// 회원정보 삭제 메소드 
				
				public void deleteMember ( MemberDTO dto) {
					
					System.out.println("deleteMember 메소드 호출됨");
					
					try {
					
					conn = 	JDBCUtil.getConnection();
					// MEMBER_DELETE = "delete member where id = ? " ;	
					pstmt = conn.prepareStatement(MEMBER_DELETE)	;
					
					// ? 변수에 값을 할당
					
					pstmt.setString(1, dto.getId());		
					
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
