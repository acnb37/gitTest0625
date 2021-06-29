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
	
	
}
