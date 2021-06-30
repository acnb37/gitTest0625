package edu.kh.semi.board.model.service;
import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


import edu.kh.semi.board.model.dao.Board2DAO;
import edu.kh.semi.board.model.dao.SelectBoardDAO;
import edu.kh.semi.board.model.vo.Attachment;
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

	/**게시글 삽입 service
	 * @param board
	 * @param atList
	 * @param boardType
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Board board, List<Attachment> atList, int boardType) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		//1. 다음 게시글 번호 얻오기
		//왜? 동시 다발적인 INSERT 발생 시 시퀀스.nextval가 연속으로 이루어져
		//이후 시퀀스.currval가 호출될 때 원하는 값을 못얻어오는 경우가 생기기 때문에....
		int boardNo = dao.nextBoardNo(conn);
		int result=0;
		//2.얻어온 boardNo가 존재할 경우 board 객체 추가 후 board를 insert
		if(boardNo>0) {
			board.setBoardNo(boardNo);
			
			//2.5 게시글 내용의 출바꿈 <br> 태그로 변환하는 작업 필요
			//+크로스 사이트 스크립팅 방지 처리
			//textarea의 개행문자: \r\n
			// div의 개행문자 : <br>
			String boardContent = board.getBoardContent();
			String boardTitle = board.getBoardTitle();
			boardTitle = replaceParameter(boardTitle);
			board.setBoardTitle(boardTitle);
			boardContent = replaceParameter(boardContent);
			boardContent = boardContent.replaceAll("\r\n", "<br>");
			board.setBoardContent(boardContent);
			result = dao.insertBoard(conn,board,boardType);
			
			//3. 게시글 부분 삽입 성공시 파일 정보 삽입
			if(result>0) {
				//1.atList를 통째로 전달해서 insert 반복 수행
				//2.atList를 통째로 전달해서 insertAll로 수행
				//3.atList에서 하나씩 꺼내서 한 행씩 insert
				
				//3번
				for(Attachment at : atList) {
					at.setBoardNo(boardNo);//게시글 번호 셋팅
					result = dao.insertAttachment(conn,at);
					
					if(result==0) {//insert 실패 시 바로rollback후 남은 구문과 수행하지 않음.
						rollback(conn);
						break;
					}
				}
				if(result>0) {
					commit(conn);
					result = boardNo;
				}else {
					rollback(conn);
				}
				
				
			}else {
				rollback(conn);
			}
		}
		
		close(conn);
		
		return result;
	}

	
	
	
	// 크로스 사이트 스크립트 방지 메소드
		private String replaceParameter(String param) {
			String result = param;
			if(param != null) {
				result = result.replaceAll("&", "&amp;");
				result = result.replaceAll("<", "&lt;");
				result = result.replaceAll(">", "&gt;");
				result = result.replaceAll("\"", "&quot;");
			}
			
			return result;
		}

	
	
	
	
	
}
