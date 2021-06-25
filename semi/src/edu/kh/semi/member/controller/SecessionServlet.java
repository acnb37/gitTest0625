package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;


@WebServlet("/member/secession")
public class SecessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/member/secession.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String currentPwd = request.getParameter("currentPwd");
		HttpSession session = request.getSession();
		System.out.println("탈퇴"+currentPwd);
		int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
		try {
		int result = new MemberService().secession(memberNo,currentPwd);
		
		// 탈퇴 성공 시 -> 메인 페이지로 이동
		// 실패 시 -> 회원 탈퇴 페이지로 이동
		String path =null;
		String icon = null;
		String title = null;
		String text = null;
		if(result>0) {
			icon = "success";
			title = "회원 탈퇴  성공";
			text = "회원 탈퇴 되었습니다.";
			path = request.getContextPath();
			session.invalidate();
		}else {
			icon = "error";
			title = "회원 탈퇴  실패";
			text = "회원 탈퇴 중 문제가 발생했습니다.";
			
			path ="secession";// 회원 탈퇴 페이지 요청 주소
		}
		//탈퇴 성공시 session 무효화 되기 때문에
		session =request.getSession();
		//2. 메세지들을 Session에 추가
		session.setAttribute("icon", icon);
		session.setAttribute("title", title);
		session.setAttribute("text", text);
		
		response.sendRedirect(path);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
