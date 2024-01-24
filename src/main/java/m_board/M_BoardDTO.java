package m_board;

import java.sql.* ;
import lombok.Data;


@Data

public class M_BoardDTO {

	private  int id;
	private  String m_title;
	private  String m_write;
	private  String m_cont;
	private  Date regdate;
	private int cnt;
	
	
	
	
}
