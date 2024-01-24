package test;

import m_board.M_BoardDAO;
import m_board.M_BoardDTO;

public class M_Board_delete {

	public static void main(String[] args) {

		M_BoardDTO dto = new M_BoardDTO();
		dto.setId(1);
		
		M_BoardDAO dao = new M_BoardDAO();
		dao.deleteBoard(dto);
		
	}

}
