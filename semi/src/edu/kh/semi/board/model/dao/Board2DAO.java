package edu.kh.semi.board.model.dao;
import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.kh.semi.board.model.vo.Attachment;
import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.Category;
import edu.kh.semi.board.model.vo.Pagination;
import edu.kh.semi.member.model.dao.MemberDAO;
public class Board2DAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop =null;
	public Board2DAO() {
		String filePath = Board2DAO.class.getResource("/edu/kh/semi/sql/board/Board2-query.xml").getPath();
		
		try {
			prop = new Properties();
			//filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**카테고리 조회
	 * @param conn
	 * @return
	 */
	public List<Category> selectCategoryList(Connection conn) throws Exception{
		// TODO Auto-generated method stub
		List<Category> category = null;
		String sql = prop.getProperty("selectCategoryList");
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			category = new ArrayList<Category>();
			while(rs.next()) {
				
				int categoryCode = rs.getInt("CATEGORY_CD");
				String categoryName = rs.getString("CATEGORY_NM");
				Category c = new Category(categoryCode, categoryName);
				category.add(c);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return category;
	}
	/** 다음 게시글 번호 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception{
		// TODO Auto-generated method stub
		int boardNo = 0;
		String sql = prop.getProperty("nextBoardNo");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
		}finally {
			close(rs);
			close(stmt);
		}
		return boardNo;
	}
	/** 게시글 삽입 DAO
	 * @param conn
	 * @param board
	 * @param boardType
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, Board board, int boardType) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getMemberNo());
			pstmt.setInt(5, board.getCategoryCode());
			pstmt.setInt(6, boardType);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	/**첨부파일(이미지) 정보 삽입 DAO
	 * @param conn
	 * @param at
	 * @return
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment at) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getFilePath());
			pstmt.setString(2, at.getFileName());
			pstmt.setInt(3, at.getFileLevel());
			pstmt.setInt(4, at.getBoardNo());
			
			result= pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	/**게시글 수정 DAO
	 * @param conn
	 * @param board
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, Board board) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		String sql = prop.getProperty("updateBaord");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getCategoryCode());
			pstmt.setInt(4, board.getBoardNo());
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	/**첨부 파일 수정 DAO
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int updateAttachment(Connection conn, Attachment at) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		String sql = prop.getProperty("updateAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, at.getFileName());
			pstmt.setInt(2, at.getBoardNo());
			pstmt.setInt(3, at.getFileLevel());
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
}
