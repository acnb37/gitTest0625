package edu.kh.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//로그아웃 : Session에 기록된 로그인된 회원 정보를 없애는 것
		request.getSession().invalidate();//세션 무효화 ->자동으로 새로운 세션이 생성됨
		
		//로그아웃 후 이동 페이지 지정
		//1)메인페이지로 이동 == 메인페이지 재요청
		//response.sendRedirect(request.getContextPath());
		//2)로그아웃을 요청한 페이지로 이동 
		response.sendRedirect(request.getHeader("referer"));
		//request.getHeader("referer") : 
		//요청된 HTTP Header에서 referer 속성에 작성된 값을 얻어옴
		//referer:요청 이전 주소를 저장하는 속성
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
