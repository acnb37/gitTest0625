package edu.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.kh.semi.board.model.service.ReplyService;
import edu.kh.semi.board.model.vo.Reply;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/reply/").length());
		
		try {
			
			ReplyService service = new ReplyService();
			
			// 댓글 목록 조회 (ajax)
			if(command.equals("list")) {
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				
				
				
				List<Reply> list = service.selectList(boardNo);
				
				//요청/응답 시 데이터는 기본적으로 문자열의 형태로 전달이 된다
				
				//해결방법이 필요 - > List가 List로 인식 되게 해야된다.
				//List == Object
				//Ajax == Javascript
				
				//JavaScript Object Notation(JSON, 자바스크립트 객체 표기법)
				//자바스크립트 객체의 모양을 하고있는 문자열
				
				// JSON 데이터 반환하기
				//Gson gson = new Gson();
				//gson.toJson(list, response.getWriter());
				//-> list를 JSON 형태로 변환하여 
				//응답용 스트림이용
				
				Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				gson.toJson(list, response.getWriter());
			}
			
			
			
			// 댓글 삽입 Controller
			else if(command.equals("insertReply")) {
				int memberNo = Integer.parseInt(request.getParameter("memberNo"));
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				String replyContent = request.getParameter("replyContent");
				
				Reply reply = new Reply();

				reply.setMemberNo(memberNo);
				reply.setBoardNo(boardNo);
				reply.setReplyContent(replyContent);
				
				
				int result = service.insertReply(reply);
				
				response.getWriter().print(result);
			}
			
			
			// 댓글 수정 Controller
			else if(command.equals("updateReply")) {
				int replyNo = Integer.parseInt(request.getParameter("replyNo"));
				String replyContent = request.getParameter("replyContent");
				
				Reply reply = new Reply();

				reply.setReplyNo(replyNo);
				reply.setReplyContent(replyContent);
				

				int result = service.updateReply(reply);
				
				response.getWriter().print(result);
			}
			
			
			// 댓글 삭제 Controller
			else if(command.equals("deleteReply")) {
				int replyNo = Integer.parseInt(request.getParameter("replyNo"));
				
				int result = service.deleteReply(replyNo);
				
				response.getWriter().print(result);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}

	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	
	
	
	
	
	
	

}
