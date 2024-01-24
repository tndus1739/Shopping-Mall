package test;

import member.MemberDAO;
import member.MemberDTO;

public class Member_Insert {

	public static void main(String[] args) {
		
		
		MemberDTO dto = new MemberDTO () ;
		
		dto.setId("ssss");
		dto.setPassword("1234");
		dto.setPhone("010-3333-1234");
		dto.setEmail("vvv@ooo");
		dto.setAddr("ppp");
		
		
		MemberDAO dao = new MemberDAO () ;
		
		dao.signIn(dto);

	}

}
