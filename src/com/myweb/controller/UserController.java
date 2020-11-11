package com.myweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

import com.myweb.user.service.UserDeleteServiceImpl;
import com.myweb.user.service.UserJoinServiceImpl;
import com.myweb.user.service.UserLoginServiceImpl;
import com.myweb.user.service.UserService;
import com.myweb.user.service.UserUpdateServiceImpl;

//1.확장자 패턴
@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public UserController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");//기본으로 해주기
		
		//2.요청 분기 작업
		String uri = request.getRequestURI();
		String conpath = request.getContextPath();
		String command = uri.substring(conpath.length());
		
//		System.out.println(uri);
//		System.out.println(conpath);
//		System.out.println(command);
		
		//MVC2 -> 가입 -> 로그인
		
		UserService service;//부모타입 선언 > 서비스가 필요할 때 마다 넘겨준다
		
		
		
		if(command.equals("/user/join.user")) {//가입(join)화면 요청
			request.getRequestDispatcher("join.jsp").forward(request, response);
	
		} else if(command.equals("/user/login.user")) { //로그인 화면 요청
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else if(command.equals("/user/joinForm.user")) { //회원가입 처리
			service = new UserJoinServiceImpl();//인터페이스로 생성한 Join서비스 처리
			int result = service.execute(request, response);//실행
			//>중복 1, 성공 0
			
			if(result == 1) {//중복>>1.Forward(서버에서 처리하기 깔끔함)
				request.setAttribute("msg", "이미 존재하는 회원입니다");//>스크립트 경고창을 띄워줄 메세지를 함께 전송
				request.getRequestDispatcher("join.jsp").forward(request, response);
				
			} else {//성공 >> 로그인 화면 이동
				response.sendRedirect("login.user");
			}
			
		} else if(command.equals("/user/loginForm.user")) {//로그인 처리
			//1.UseLoginServiceImpl()로 연결
			//2.폼값을 받아서 DAO의 login메서드를 이용해서 로그인 처리를 합니다
			//3.로그인 성공시 user라는 이름으로 session에 UserVO를 저장
			//mypage에 MVC2방식으로 이동
			//4.로그인 실패시 msg에 "아이디 비밀번호를 확인하세요"를 담아서 login.jsp로 이동
			//자바에서 세션을 얻는 방법(세션타입 리턴)
			//HttpSession session = request.getSession();
			
			service = new UserLoginServiceImpl();
			int result = service.execute(request, response);
			System.out.println(result);
			
			if(result == 1) {//로그인 성공
				response.sendRedirect("mypage.user");
				
			} else {//로그인 실패
				request.setAttribute("msg", "아이디 비밀번호를 확인하세요");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
				
		} else if(command.equals("/user/mypage.user")) {//mypage 화면 처리 요청
			request.getRequestDispatcher("mypage.jsp").forward(request, response);
			
		} else if(command.equals("/user/logout.user")) {//로그아웃 처리	
			HttpSession session = request.getSession();//자바에서 세션 구하는법(중요)
			session.invalidate();//로그인 세션 삭제
			response.sendRedirect(request.getContextPath());//>홈으로 보내주기
		
		} else if(command.equals("/user/update.user")) {//수정 화면 이동 처리
			//user세션에 값이 들어있다
			request.getRequestDispatcher("update.jsp").forward(request, response);
			
		}else if(command.equals("/user/updateForm.user")) {
			/*
			 * 값을 받아서 성공 실패의 결과만 받아옴
			 * 1.UserUpdateServiceImpl() 생성 호출
			 * 2.execute메서드에서는 update() 메서드를 실행시키고 성공실패 여부를 받아서 컨트롤로 리턴
			 * 
			 */
			service = new UserUpdateServiceImpl();
			int result = service.execute(request, response);
			
			if(result == 1) {
				//문자열의 형태로 스크립트를 작성해서 Out.println()화면에 전달
				//잘 사용하지 않는 옛날 방식
				response.setContentType("text/html; charset=UTF-8");//html출력 타입
				PrintWriter out = response.getWriter();//PrintWriter(출력스트림)
				out.println("<script>");
				out.println("alert('회원정보가 수정되었습니다');");
				out.println("location.href='mypage.jsp';");
				out.println("</script>");

				
//				request.getRequestDispatcher("mypage.jsp").forward(request, response);
			} else {//실패시 9
				response.sendRedirect("mypage.user");//실패시 마이페이지로 이동
			}
			
		} else if(command.equals("/user/delete.user")) { //delete화면 처리
			request.getRequestDispatcher("delete.jsp").forward(request, response);
			
		} else if(command.equals("/user/deleteForm.user")) {//delete작업처리
			
			/*
			 * 1. UserDeleteServiceImpl()로 생성, 연결
			 * 2. delete()메서드를 통해 삭제 처리
			 * 3. 성공실패결과를 컨트롤러로 받아와서 성공시 - 세션삭제 후 홈화면으로 리다이렉트
			 * 4. 실패시 - 실패메시지를 delete.jsp로 처리해주세요
			 */
			service = new UserDeleteServiceImpl();
			int result = service.execute(request, response);
			
			if(result == 1) {//삭제 성공
//				updateImpl에서 세션 삭제 진행
//				HttpSession session = request.getSession();
//				session.invalidate();//세션 삭제
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('정상적으로 탈퇴 되었습니다');");//????왜 안나오징
				out.println("</script>");
				
				response.sendRedirect(request.getContextPath());//홈화면
						
			} else {//삭제 실패
				request.setAttribute("msg", "비밀번호를 확인하세요");
				request.getRequestDispatcher("delete.jsp").forward(request, response);
			}
			
			
			
		}
		
		
		
		
	}

	
}











































