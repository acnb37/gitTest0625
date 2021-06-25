package edu.kh.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;


@WebServlet("/member/idDupCheck")
public class idDupCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/idDupCheck.jsp");
		view.forward(request, response);
	}

	//DB에 같은 아이디가 있는지 중복 검사 후 결과 반환
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String id = request.getParameter("id");
	try {
		int result = new MemberService().idDupCheck(id);
		// ajax는 화면 전체가 아닌
		//화면 일부 갱신에 사용되는 데이터만 응답으로 내보낸다.
		
		//응답을 받을 클라이언트와의 연결 스트림
		PrintWriter out = response.getWriter();
		out.print(result);
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	
	//팝업창으로 중복 검사
//	try {
//		int result = new MemberService().idDupCheck(id);
//		//System.out.println(result);
//		
//		HttpSession session = request.getSession();
//		session.setAttribute("result", result);
//		session.setAttribute("id", id);
//		
//		response.sendRedirect("idDupCheck");
//	}catch(Exception e) {
//		e.printStackTrace();
//		
//		request.setAttribute("errorMasg", "아이디 중복 검사 과정에서 문제가 발생했습니다.");
//		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
//		view.forward(request, response);
//	}
	
	}

}
