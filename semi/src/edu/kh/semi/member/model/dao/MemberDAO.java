package edu.kh.semi.member.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.semi.member.model.vo.Member;

//DAO(Date Access Object) : DB 연결 객체
public class MemberDAO {

	//자주 사용하는 JDBC 객체 참조 변수 선어
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//외부 XML 파일에 작성된 SQL 구문을 읽어와 저장할 Properties객체 참조 변수 선언
	private Properties prop =null;
	
	//기본 생성자를 이용한 DAO 객체 생성시
	//외부 XML파일을 읽어와 prop에 저장
	public MemberDAO() {
		// TODO Auto-generated constructor stub
		//member-query.xml 파일의 경로 얻어오기
		String filePath = MemberDAO.class.getResource("/edu/kh/semi/sql/member/member-query.xml").getPath();
		
		try {
			prop = new Properties();
			//filePath 변수에 저장된 경로로 부터 XML 파일을 읽어와 prop에 저장
			prop.loadFromXML(new FileInputStream(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/** 로그인 DAO
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, String memberId, String memberPw)throws Exception {
		// TODO Auto-generated method stub
		Member loginMember = null;
		
		String sql = prop.getProperty("login");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, memberId);
			pstmt.setNString(2, memberPw);
			
			//SQL 구문 수행후 조회 결과인 ResultSet을 rs 변수에 저장
			rs = pstmt.executeQuery();
			
			//조회 결과가 있는지 확인 후 있으면 Member 객체를 생성하여 조회된 값을 저장
			//->로그인 결과는 없거나 1행만 있음 ->if문으로 검사
			if(rs.next()) {
				loginMember = new Member(rs.getInt("MEMBER_NO"),
						rs.getString("MEMBER_ID"),
						rs.getString("MEMBER_NM"),
						rs.getString("MEMBER_PHONE"),
						rs.getString("MEMBER_EMAIL"),
						rs.getString("MEMBER_ADDR"),
						rs.getDate("ENROLL_DATE"),
						rs.getString("MEMBER_GRADE"));
			}
			
		}finally {
			//사용한 JDBC 객체 생성 역순으로 반환
			close(rs);
			close(pstmt);
		}
		
		//조회 결과 반환(조회 성공시 Member, 실패 시 null 이 반환됨)
		return loginMember;
	}

	/**회원가입 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception{
		// TODO Auto-generated method stub
		int result = 0;
		String sql = prop.getProperty("signUp");
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberAddress());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**아이디 중복 확인 DAO
	 * @param conn
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String id) throws Exception {
		// TODO Auto-generated method stub
		int result=-1;
		String sql = prop.getProperty("idDupCheck");
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs= pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/**회원 정보 수정 DAo
	 * @param conn
	 * @param memberEmail
	 * @param memberPhone
	 * @param memberAddress
	 * @param memberNo 
	 * @return result
	 * @throws Exception
	 */
	public int memberUpdate(Connection conn, String memberEmail, String memberPhone, String memberAddress, int memberNo) throws Exception{
		// TODO Auto-generated method stub
		int result = -1;
		String sql = prop.getProperty("memberUpdate");
		try {
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			pstmt.setString(2, memberPhone);
			pstmt.setString(3, memberAddress);
			pstmt.setInt(4, memberNo);
			result=pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**비밀번호 변경 DAO
	 * @param conn
	 * @param currentPwd
	 * @param newPwd1
	 * @param memberNo 
	 * @return result
	 * @throws Exception
	 */
	public int changePwd(Connection conn, String currentPwd, String newPwd1, int memberNo) throws Exception{
		// TODO Auto-generated method stub
		int result =-1;
		String sql = prop.getProperty("changePwd");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPwd1);
			pstmt.setString(2, currentPwd);
			pstmt.setInt(3, memberNo);
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int secession(Connection conn, int memberNo, String currentPwd)throws Exception {
		// TODO Auto-generated method stub
		int result =-1;
		String sql = prop.getProperty("secession");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,memberNo);
			pstmt.setString(2,currentPwd);
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
