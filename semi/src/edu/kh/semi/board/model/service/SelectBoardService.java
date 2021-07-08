package edu.kh.semi.board.model.service;
import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import edu.kh.semi.board.model.dao.SelectBoardDAO;
import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.Pagination;
public class SelectBoardService {

	private SelectBoardDAO dao = new SelectBoardDAO();

	/**페이징 처리 객체 생성용 Service
	 * @param cp
	 * @param boardType
	 * @return pagination
	 * @throws Exception
	 */
	public Pagination getPagination(int cp, int boardType)throws Exception {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		//DB에서 전체 게시글 수 + 게시판 이름을 얻어옴
		Map<String, Object> map = dao.getListCount(conn,cp,boardType);
		
		close(conn);
		
		int listCount = map.get("listCount") != null ? (int)map.get("listCount") : 0;

		String boardName = (String)map.get("boardName");
		System.out.println(boardName);
		return new Pagination(cp, listCount, boardType, boardName);
	}

	/**게시글 목록 조회 Service
	 * @param pagination
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		List<Board> boardList=dao.selectBoardList(conn,pagination);
		close(conn);
		
		return boardList;
	}

	/**게시글 상세 조회
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo)throws Exception {
		Connection conn = getConnection();
		
		Board board = dao.selectBoard(conn,boardNo);
		
		if(board.getBoardTitle() !=null) {
			int result = dao.increaseReadCount(conn,boardNo);
			if(result>0) {
				commit(conn);
				board.setReadCount(board.getReadCount()+1);
			}
			else         rollback(conn);
		}
		close(conn);
		return board;
	}
	
	
	
	//검색
	//-------------------------------------
	//검색 조건에 따라  SQL에 사용할 조건식을 만들어 반환하는 메소드
	private String createCondition(String searchKey,String searchValue) {
		String condition = null;
		//condition 양끝에는 띄어쓰기를 반드시 추가하여
		//sql구문이 연속되서 작성되는 것을 반지함
		switch(searchKey) {
		case "title":
			condition = "AND BOARD_TITLE LIKE '%" + searchValue + "%'";
			break;
		case "content":
			condition = "AND BOARD_CONTENT LIKE '%" + searchValue + "%'";
			break;
		case "titcont":
			condition = " AND (BOARD_TITLE LIKE '%" + searchValue+ "%' " +
					" OR BOARD_CONTENT LIKE '%" + searchValue+ "%') ";
			break;
		case "writer":
			condition = "AND MEMBER_NM LIKE '%" + searchValue + "%'";

			break;
		}
		return condition;
	
	}
	
	
	/**페이징 처리 객체 생성용 Service(검색용)
	 * @param cp
	 * @param boardType
	 * @param searchKey
	 * @param searchValue
	 * @return pagination
	 * @throws Exception
	 */
	public Pagination getPagination(int cp, int boardType,String searchKey,String searchValue)throws Exception {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		String condition = createCondition(searchKey, searchValue);
		//DB에서 전체 게시글 수 + 게시판 이름을 얻어옴
		Map<String, Object> map = dao.getListCount(conn,cp,boardType,condition);
		
		close(conn);
		
		int listCount = map.get("listCount") != null ? (int)map.get("listCount") : 0;

		String boardName = (String)map.get("boardName");
		System.out.println(boardName);
		return new Pagination(cp, listCount, boardType, boardName);
	}

	/**게시글 목록 조회 Service (검색용)
	 * @param pagination
	 * @return boardList
	 * @param searchKey
	 * @param searchValue
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination,String searchKey,String searchValue) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		//조건식 생성
		String condition = createCondition(searchKey, searchValue);
		List<Board> boardList=dao.selectBoardList(conn,pagination,condition);
		close(conn);
		
		return boardList;
	}
	
}
