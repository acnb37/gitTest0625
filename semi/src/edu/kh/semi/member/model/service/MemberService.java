package edu.kh.semi.member.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.semi.member.model.dao.MemberDAO;
import edu.kh.semi.member.model.vo.Member;
//Service: 비즈니스 로직 처리(데이터 가공, 트랜잭션 처리)
public class MemberService {
	private MemberDAO dao = new MemberDAO();

	/**로그인 Service
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		//얻어온 Connection과 매개변수를 DAO의 알맞은 메소드로 전달하여 결과를 받환 받음.
		Member loginMember = dao.login(conn,memberId,memberPw);
		
		close(conn);
		return loginMember;
	}

	/**회원가입 Service
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = dao.signUp(conn,member);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/** 아이디 중복 확인 Service
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String id)throws Exception {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = dao.idDupCheck(conn,id);
		close(conn);
		return result;
	}

	/** 회원 정보 업데이트
	 * @param memberEmail
	 * @param memberPhone
	 * @param memberAddress
	 * @param memberNo 
	 * @return result
	 * @throws Exception
	 */
	public int memberUpdate(String memberEmail, String memberPhone, String memberAddress, int memberNo) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = dao.memberUpdate(conn,memberEmail,memberPhone,memberAddress,memberNo);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	/**비밀번호변경 Service
	 * @param currentPwd
	 * @param newPwd1
	 * @param memberNo 
	 * @return result
	 * @throws Exception
	 */
	public int changePwd(String currentPwd, String newPwd1, int memberNo)throws Exception {
		// TODO Auto-generated method stub
		Connection conn =getConnection();
		int result = dao.changePwd(conn,currentPwd,newPwd1,memberNo);
		if(result>0)commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	/** 회원탈퇴 Service
	 * @param memberNo
	 * @param currentPwd
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo, String currentPwd)throws Exception {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		int result = dao.secession(conn,memberNo,currentPwd);
		if(result>0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
}
