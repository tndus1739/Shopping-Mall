package test;

import member.MemberDAO;
import member.MemberDTO;

public class Login {

	public static void main(String[] args) {
		// 1. MemberDTO : id, password
		
		MemberDTO dto = new MemberDTO () ;
				dto.setId("user");
				dto.setPassword("1234");
				
				
				// 2. USersDAO : login (dto) 리턴된 값이 null이라면 인증 실패 , 그렇지 않으면 인증 성공
				MemberDAO dao = new MemberDAO () ;

				// 3. 리턴 받을 UsersDTO 선언
				
				MemberDTO user = new MemberDTO () ;
				
				user = dao.login(dto);
				
				if ( user != null) {
					
					System.out.println("인증 성공함");
					System.out.println(user);
				} else  {
					
					System.out.println("인증실패함");
					System.out.println(user);
				}
				

	}

}
