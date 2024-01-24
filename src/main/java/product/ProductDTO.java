package product;

import java.sql.* ;
import lombok.Data;


@Data


public class ProductDTO {

	private int id ;
	private String name ;
	private int price ;
	private String content ;
	private Date regdate ;
	
	
	
}
