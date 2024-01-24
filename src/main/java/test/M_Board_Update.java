package test;

import m_board.M_BoardDAO;
import m_board.M_BoardDTO;

public class M_Board_Update {

	public static void main(String[] args) {
	
		
		M_BoardDTO dto  = new M_BoardDTO() ;
		
		dto.setM_title("제목 수정");
		dto.setM_write("이름수정");
		dto.setM_cont("내용수정");
		dto.setId(1);
		
		
		
		M_BoardDAO dao  = new M_BoardDAO() ;
		
		dao.updateBoard(dto);
		
	}

}
