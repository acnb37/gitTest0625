package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.View;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		int memberNo = loginMember.getMemberNo();

		// 3.파라미터로 전달 받은 수정된 회원정보를 변수에 저장
		// (DB에 저장하기위해 알맞은 형태로 변환, 회원가입 Servlet 참고)
		String memberEmail = request.getParameter("email");
		String[] phone = request.getParameterValues("phone");
		String memberPhone = String.join("-", phone);

		String[] address = request.getParameterValues("address");
		// 주소는 필수 입력 사항이 아니기 때문에 파라미터가 없을 수 있다 == null
		// -> String.join()수행 시 NullPointer가 발생 할 수 있다.
		String memberAddress = null;
		if (address != null) {
			memberAddress = String.join(",", address);
		}
		try {
			// 4. 회원 정보 수정 서비스를 호출 후 결과 반환 받기
			int result = new MemberService().memberUpdate(memberEmail, memberPhone, memberAddress, memberNo);

			// 5. 수정 결과에 따라서 화면에 sweetalert로
			// 수정 성공/ 실패 메세지 출력하도록 session에 속성 세팅
			String icon = null;
			String title = null;
			String text = null;
			if (result > 0) {
				icon = "success";
				title = "회원 정보 수정 성공";
				text = "회원 정보가 수정 되었습니다.";
				// 6. 로그인된 회원 정보를 최신 버전으로 업데이트
				//최신 정보 업데이트
				loginMember.setMemberPhone(memberPhone);
				loginMember.setMemberEmail(memberEmail);
				loginMember.setMemberAddress(memberAddress);
			} else {
				icon = "error";
				title = "회원 정보 수정 실패";
				text = "회원 정보 수정 중 문제가 발생했습니다. \n문제가 지속될 경우 고객센터 문의 바랍니다.";
			}
			session.setAttribute("icon", icon);
			session.setAttribute("title", title);
			session.setAttribute("text", text);
		

			
			// 7. 마이페이지로 재요청
			response.sendRedirect(request.getContextPath()+"/member/myPage");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "회원 정보 수정 과정에서 문제가 발생했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/error.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
