package edu.kh.semi.board.model.service;
import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


import edu.kh.semi.board.model.dao.Board2DAO;
import edu.kh.semi.board.model.dao.SelectBoardDAO;
import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.Category;
import edu.kh.semi.board.model.vo.Pagination;
public class Board2Service {

	private Board2DAO dao = new Board2DAO();

	/** 카테고리 목록 조회
	 * @return
	 * @throws Exception
	 */
	public List<Category> selectCategoryList() throws Exception{
		// TODO Auto-generated method stub
		Connection conn =getConnection();
		List<Category> category = dao.selectCategoryList(conn);
		close(conn);
		return category;
	}

	
}
