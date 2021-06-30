package edu.kh.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.semi.board.model.service.Board2Service;
import edu.kh.semi.board.model.service.SelectBoardService;
import edu.kh.semi.board.model.vo.Attachment;
import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.Category;
import edu.kh.semi.board.model.vo.Pagination;
import edu.kh.semi.common.MyFileRenamePolicy;
import edu.kh.semi.member.model.vo.Member;

//MVC (Model View Controller)

//Model : 비즈니스 로직 처리
//-Service :데이터 가공, 트랜잭션 처리
//-DAO : DB연결 , SQL 수행
//-VO : 데이터 저장(db 조회 결과, 다수 파라미터 저장)

//View :클라이언트에게 보여지는 부분 클라이언트의 요청을 받고,
//		응답(요청 처리 결과)을 보여주는 화면

//Controller:클라이언트의 요청을 받아서 알맞은 비즈니스 로직으로 처리한 후
//			 응답화면을 연결하는 제어 역할





@WebServlet("/board2/*")
public class Board2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	//Front Controller 패턴(디자인 패턴)
	// - 클라이언트의 요청을 한 곳으로 집중시켜
	//   개발 및 유지보수의 효율성을 증가시킨 패턴
	
	//-요청에 따른 Servlet을 각각 생성하는 것이 아닌, 하나의 Servlet으로 여러 요청을 받음.


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();// 요청 주소                   // /semi/board/list
		String contextPath = request.getContextPath();// 최상위 주소 // /semi
		String command = uri.substring((contextPath+"/board2/").length());//list
		//uri에서 contextPath+"/board/" 만큼을 앞에서 부터 잘라낸 후 나머지를 command 저장
		
		String path= null; //응답화면 주소 또는 경로
		RequestDispatcher view = null; //요청 위임 객체 저장 참조 변수
		
		//sweetalert용 변수
		String icon =null;
		String title =null;
		String text =null;
	
		
		try {
			Board2Service service = new Board2Service();
			
			//현재 페이지
			// 삼항 연산자를 이용해서 cp가 없으면 1, 있으면 int형태로 파싱한 cp값을 저장
			int cp =request.getParameter("cp")== null ? 1 : 
					Integer.parseInt(request.getParameter("cp"));
			
			//게시글 등록 화면 번환 Controller
			if(command.equals("insertForm")) {
			// 카테고리 목록 조회
			List<Category> category = service.selectCategoryList();
			//System.out.println(category);
			request.setAttribute("category", category);
			path="/WEB-INF/views/board/boardInsert.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
			}
			//게시글 삽입 Controller
			else if (command.equals("insert")) {
				//categoryCode
				//boardTitle
				//memberNo(session)
				//img0~3
				//boardContent
				//type(boardType)
				//int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
				int boardType = Integer.parseInt(request.getParameter("type"));
				//String boardTitle = request.getParameter("boardTitle");
				//String boardContent = request.getParameter("boardContent");
				
				HttpSession session = request.getSession();
				int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
				//System.out.println(categoryCode);
				//System.out.println(boardTitle);
				//System.out.println(boardContent);
				System.out.println(memberNo);
				System.out.println(boardType);
				
				//*문제점 : POST 방식으로 전달된 데이터가 NULL로 표기됨
				//		  그런데 GET 방식 전달 데이터는 잘 넘어옴
				//*원인 : form 태그의 encType=multipart/form-data 때문
				//*해결 방법 : MulipartRequest를 이용하여 파라미터를 얻어오면 해결됨
				
				//** MultipartRequest 생성 준비
				// -cos.jar 라이브러리에서 제공하는 multipart/form-data 형식의 요청을
				// 받아 쉽게 처리할 수 있는 객체
				
				//1.전송되는 파일의 크기 제한 수치를 지정
				//1kb == 1024Byte
				//1MB == 1024KB
				
				int maxSize = 1024*1024*20;   //20MB - > byte 
				
				//2.업로드 되는 파일이 실제로 저장될 서버 경로
				String root = session.getServletContext().getRealPath("/");
				System.out.println("root"+root);
				String filePath= "resources/images/";
				switch(boardType) {
				case 1: filePath+= "freeboard/";break;
				case 2: filePath+= "infoboard/";break;
				}
				
				//실제 저장 경로
				System.out.println("실제 저장 경로 : "+root+filePath);
				
				//3. 저장되는 파일명 변환 작업
				//파일명 중복으로 인한 문제를 해결하기 위해서 변환 작업이 필요하다.
				//-> MyRenamePolicy 클래스 파일 생성
				
				//4.MultipartRequest 객체 생성
				//** 객체가 생성되는 순간
				//   전달 된 파라미터 중 파일 관련 데이터는 지정된 경로에 파이롤 바로 저장된다!!!
				MultipartRequest mpRequest = new MultipartRequest(request, root+filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
																//기존 요청 관련 객체, 파일실제저장경로,용량제한,요청 중파일이 아닌 파라미터의 문자 인코딩, 파일명 변경 객체
				
				//5 전달 받은 파라미터 중 첨부파일(이미지)를 다루는 방법
				
				//5-1 DB에 이미지 정보를 모아서 전달할 List 생성
				List<Attachment> atList = new ArrayList<Attachment>();
				
				//5-2 MultipartRequest에서 이미지 정보를 모두 얻어옴
				Enumeration<String> images = mpRequest.getFileNames();
				//Enumeration : Iterator의 과거 버전
				//Iterator : 컬렉션에 저장된 요소를 순차 접근하는 반복 접근자.
				
				//5-3 얻어온 파일 정보를 반복 접근하여 atList에 순서대로 담기
				while(images.hasMoreElements()) {
					
					String name = images.nextElement();//다음 요소(값) 얻어오기
					System.out.println("input type=file 의 name 속성값:"+ name);
					
					System.out.println("변경된 파일명 :"+mpRequest.getFilesystemName(name));
					System.out.println("변경전 파일명 :"+mpRequest.getOriginalFileName(name));
					
					//실제 파일이 업로드가 되지 않아도
					//비어있는 input type=file 태그가 넘어온다.
					// 대신 변경전/후 파일명은 null이다.
					
					if(mpRequest.getFilesystemName(name) !=null) {
						//파일 정보 저장용 객체 생성
						Attachment at = new Attachment();
						//파일 경로, 변경된 파일명, 파일 레벨
						at.setFilePath(filePath);//웹상 접근 경로만 저장
						at.setFileName(mpRequest.getFilesystemName(name));
						at.setFileLevel(Integer.parseInt(name.substring("img".length())));
						
						atList.add(at);
					}
				}
				
				for(Attachment a : atList) {
					System.out.println(a);
				}
				
				//6. 파일 외에 게시글 관련 정보를 MultipartRequest에 얻어오기
				String boardTitle = mpRequest.getParameter("boardTitle");
				String boardContent = mpRequest.getParameter("boardContent");
				int categoryCode = Integer.parseInt(mpRequest.getParameter("categoryCode"));
				
				//7. 게시글 관련 정보를Board 객체에 저장하기
				Board board = new Board();
				board.setBoardTitle(boardTitle);
				board.setBoardContent(boardContent);
				board.setCategoryCode(categoryCode);
				board.setMemberNo(memberNo);
				
				//8. 게시글 정보와 이미지를 삽입하는 service 호출
				int result = service.insertBoard(board,atList,boardType);
				// result = boardNo or 0;
				
				//9. 삽입 결과에 따른 결과 화면 제어
				if(result>0) {
					icon = "success";
					title= "게시글 등록 성공";
					path=request.getContextPath()+"/board/view?no="+result+"&cp=1&type="+boardType;
				}else {
					icon="error";
					title="게시글 등록 실패";
					
					path=request.getHeader("referer");
				}
				session.setAttribute("icon", icon);
				session.setAttribute("title", title);
				response.sendRedirect(path);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
