package test;

import m_board.M_BoardDAO;
import m_board.M_BoardDAO;
import m_board.M_BoardDTO;

public class M_Board_insert {

	public static void main(String[] args) {

		M_BoardDTO dto = new M_BoardDTO () ;
		
		dto.setM_title("제목66");
		dto.setM_write("글쓴이66");
		dto.setM_cont("내용66");
	
		
		M_BoardDAO dao = new M_BoardDAO ();
		
		dao.insertBoard(dto);
		
		

	}

}
