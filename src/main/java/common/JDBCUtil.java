package common;

import java.sql.*;

public class JDBCUtil {
	
	// DataBase 연결 ( Connection ) 객체 : Oracle 
	
	// 기본생성자 ( 클래스이름과 동일 )
	public JDBCUtil () {		
		 System.out.println("JDBCUtil 호출 성공");
	}
	
	// static 메소드 : 객체 생성 없이 클래스 이름으로 호출됨 
	public static Connection getConnection   ( )   {  // 리턴타입 : Connection 
	
		Connection conn = null ;
		
		// 오라클 DB : XE , C##HR , 1234 --> 연결하는 구문 생성
		
		String driver = "oracle.jdbc.driver.OracleDriver";     
		String url = "jdbc:oracle:thin:@localhost:1521:XE";	   
	 
		// 예외 설정 : 오류가 발생하더라도 프로그램이 중지되지 않도록 설정
		try {                             				  
			                              // try 블락에서 오류 발생시 catch 블락이 작동
			// try 블락				
			Class.forName(driver);			// 위의 driver 를 로드
			conn = DriverManager.getConnection(url , "C##HR28" , "1234");  // url : 오라클의 1521번 데이터베이스에 접속하겠다. 
			
			System.out.println("DB 연결이 성공했습니다.");
			
			// 위의 url 집어넣음
		    //JSP 에서 출력하라
		    System.out.println("DB 연결이 성공했습니다.");					// 정상작동됐으면 주석처리
			
			
		}catch (Exception e) {
			// catch 불락: try 블락에 오류가 있을 때만 작동
			
			System.out.println("DB 연결이 실패했습니다.");
			
					
			// DB 연결이 실패시 오류난 정보를 콘솔에 자세하게 출력
			e.printStackTrace();
								
				
		}
		
	
		
		return conn ;
}
	
	
	
	// 객체 반납 메소드
	
	public static void close (PreparedStatement pstmt , Connection conn) {
		
		if ( pstmt != null) {
			try {
				pstmt.close();
				System.out.println("pstmt가 잘 제거되었습니다.");
				
			} catch (Exception e) {
				System.out.println("pstmt가  제거 중 오류가 발생되었습니다.");
			}
			
		}
		
		if (conn != null) {
			try {
				
				conn.close();
				System.out.println("conn이 잘 제거되었습니다.");
				
			} catch (Exception e) {
				System.out.println("conn이  제거 중 오류가 발생되었습니다.");
			}
		}
		
	}
	
	// rs. pstmt , conn  반납 : select 문을 사용하고 반납	
	// 메소드 오버로딩
	public static void close ( ResultSet rs , PreparedStatement pstmt , Connection conn) {
		if ( rs != null) {
			try {
				rs.close();
				System.out.println("rs가 잘 제거되었습니다.");
				
			} catch (Exception e) {
				System.out.println("rs가  제거 중 오류가 발생되었습니다.");
			}
			
		}

		if ( pstmt != null) {
			try {
				pstmt.close();
				System.out.println("pstmt가 잘 제거되었습니다.");
				
			} catch (Exception e) {
				System.out.println("pstmt가  제거 중 오류가 발생되었습니다.");
			}
			
		}
		
		if (conn != null) {
			try {
				
				conn.close();
				System.out.println("conn이 잘 제거되었습니다.");
				
			} catch (Exception e) {
				System.out.println("conn이  제거 중 오류가 발생되었습니다.");
			}
		}

		
	}
	
	
}