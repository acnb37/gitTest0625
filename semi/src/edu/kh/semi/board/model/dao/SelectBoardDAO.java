package edu.kh.semi.board.model.dao;
import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.semi.member.model.dao.MemberDAO;
public class SelectBoardDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop =null;
	public SelectBoardDAO() {
		String filePath = SelectBoardDAO.class.getResource("/edu/kh/semi/sql/board/selectBoard-query.xml").getPath();
		
		try {
			prop = new Properties();
			//filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
