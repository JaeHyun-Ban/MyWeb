package com.myweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1. 확장자 패턴으로 변경 *.xxx로 맵핑을 변경
//>.test로 끝나는 모든것들은 이곳을 거쳐간다
@WebServlet("*.test")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public TestController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dispatchServlet(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}
	
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doAction으로 doGet과 doPost를 함께받아서 동시에 처리해준다(편함)
		
		//3. 필요한 요청 경로만 추출
		String uri = request.getRequestURI();//전체 주소(경로)
		String conPath = request.getContextPath();
		
		//>conPath의 길이만큼 앞부분을 잘라내서 뒷 경로만 얻어온다
		String command = uri.substring(conPath.length());
		
		System.out.println("uri: " + uri);//>/MyWeb/*.test
		System.out.println("conPath: " + conPath);//>/MyWeb
		System.out.println("commant: " + command);//>/###.test
		
		//한글처리도 한번에 해줄 수 있다
		request.setCharacterEncoding("utf-8");
		
		if(command.equals("/controller/login.test")) {
			//..로그인 처리..
		} else if(command.equals("/controller/logout.test")) {
			//..로그아웃 처리..
		}
		
		
		
		
		
		
	}
	
	
	
}

































































































