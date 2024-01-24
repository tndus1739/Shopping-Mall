package test;

import product.ProductDAO;
import product.ProductDTO;

public class Product_Insert {

	public static void main(String[] args) {

		ProductDTO dto = new ProductDTO () ;
		
		dto.setName("111111");
		dto.setPrice(200022);
		dto.setContent("제품설명");
		
		ProductDAO dao = new ProductDAO () ;
		
		dao.insertProduct(dto);
		
		
	}

}
